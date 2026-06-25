# eSign Library CLI Kit (Java)

A command-line interface for eMudhra's open-source [eSign Library](https://github.com/emudhra-integration-sdk/java-esign-sdk). It wraps the library's signing API so you can drive PDF eSign workflows from the terminal, scripts, and CI/CD pipelines using simple JSON files for input and output.

## Table of Contents
- [Overview](#overview)
- [Requirements](#requirements)
- [Building from Source](#building-from-source)
- [How It Works](#how-it-works)
- [CLI Usage](#cli-usage)
- [Methods and Input JSON](#methods-and-input-json)
- [Output JSON](#output-json)
- [License](#license)

---

## Overview

The CLI is a thin wrapper: for every run it reads one JSON request file, calls a single eSign library method, and writes the library's response to one JSON output file. All cryptographic and gateway logic lives in the bundled `eSignASPLibrary5_5.jar`.

eMudhra's eSign service uses a **two-phase remote signing** model:

1. **Pre-sign** (`getGatewayParam`) — the SDK hashes the PDF locally, builds a signed request with your ASP PFX certificate, and returns a `gatewayParameter` you POST to the eSign gateway to redirect the signer for authentication (OTP / Fingerprint / IRIS / Face).
2. **Post-sign** (`getSignedPdf`) — after the signer authenticates, the gateway returns a response; the SDK injects the signature back into the PDF.

> **No licence file required.** This kit targets the open-source library, which is configured with your `aspId` and the eSign gateway URLs directly in the input JSON — there is no `.lic` file.

---

## Requirements

- **Java**: JDK/JRE **1.8** (the project targets `source/target 1.8`)
- **Apache Ant**: to build from source (optional — see the `javac` fallback below)
- A valid eMudhra **ASP ID** and **ASP PFX certificate** (path/password/alias) to run real signing requests

All Java dependencies are checked into `lib/` — no external download or absolute-path configuration is needed.

---

## Building from Source

### Using Apache Ant

```bash
ant clean
ant jar
```

The output is `dist/eSignASPLibraryCLI.jar` with its dependencies copied to `dist/lib/`. The jar's manifest sets a `Class-Path` pointing at `lib/`, so keep that folder beside the jar when running.

### Without Ant (direct javac)

```bash
# Windows uses ';' as the classpath separator
javac -encoding UTF-8 -cp "$(printf '%s;' lib/*.jar)" -d build/classes $(find src -name '*.java')
```

The classpath references all JARs in `lib/`, including the core `eSignASPLibrary5_5.jar`. To upgrade the library, rebuild it from the `java-esign-sdk` repo and replace `lib/eSignASPLibrary5_5.jar` (update the filename in `nbproject/project.properties` if the version changes).

---

## How It Works

```
input.json ──▶ ESignASPLibraryCLI ──▶ eSignASPLibrary (com.emudhra.esign) ──▶ output.json
                 │
                 ├─ deserialize input JSON (Gson) into a request DTO
                 ├─ initLibrary(): build eSign object from aspId + URLs + PFX + proxy
                 ├─ call the one library method for the chosen -method
                 └─ wrap the result in CLIOutput and serialize to output JSON
```

On success the program prints `Successful`; on a handled error it prints the stack trace followed by `Failure`. The detailed error always lands in the output JSON's `errorCode` / `errorMessage` fields.

---

## CLI Usage

```bash
java -jar eSignASPLibraryCLI.jar -method <METHOD> -input <input.json> -output <output.json>
```

All three switches are mandatory. `-method` is matched case-insensitively.

| `-method`             | Purpose |
|-----------------------|---------|
| `getGatewayParam`     | Phase 1 — build the gateway redirect parameter for one or more PDFs |
| `getSignedPdf`        | Phase 2 — produce the signed PDF from the gateway response |
| `getTransactionStatus`| Poll the status of a transaction |
| `performBankKYC`      | Perform bank-account KYC for a transaction |

Example:

```bash
java -jar eSignASPLibraryCLI.jar \
  -method getGatewayParam \
  -input  Resources/inputs/getGatewayParam_Input.json \
  -output gatewayParam_out.json
```

Ready-to-edit sample input/output files for every method are in `Resources/inputs/` and `Resources/outputs/`.

---

## Methods and Input JSON

### Common fields (all methods)

Every input shares these connection/credential fields:

| Field | Description |
|-------|-------------|
| `aspId` | Your eMudhra ASP ID |
| `eSignURL` | V3 (PAN) eSign gateway URL |
| `eSignURLV2` | V2 (Aadhaar) eSign gateway URL |
| `pfxPath` | Path to the ASP PKCS#12 (`.pfx`) certificate |
| `pfxPassword` | PFX password |
| `pfxAlias` | Key alias inside the PFX |
| `proxyReq`, `proxyIp`, `proxyPort`, `proxyUserID`, `proxyUserPassword` | Optional HTTP proxy settings |
| `sessionTimeout` | Connection timeout in ms (`0` = library default) |

### `getGatewayParam`

Adds: `inputs` (array — one entry per document), `signerID`, `transactionID`, `responseURL`, `redirectURL`, `tempFolderPath`, `eSignType` (`"V2"` or `"V3"`), `authMode` (`"OTP"`, `"FingerPrint"`, `"IRIS"`, `"FaceRecognition"`).

Each entry in `inputs` is a document spec:

| Field | Notes |
|-------|-------|
| `appearanceType` | `StandardSignature`, `SignatureImage`, `advanceSignature`, `OneLiner`, `ColoredGraphic`, `BackgroundImage` |
| `base64doc` | Base64 of the PDF — or the precomputed hash when `isHash` is `true` |
| `isHash` | `true` to sign a hash instead of a full PDF |
| `docInfo`, `docURL` | Document metadata shown on the gateway |
| `signerName`, `reason`, `location` | Appearance text |
| `pageCoordinates` | Page-level placement, e.g. `"1-100,100,50,200;2-..."` |
| `coSign`, `rightOrigin` | Co-sign / coordinate-origin flags |
| `imageSignBase64` | Base64 image for image/background/advance appearances |
| `featureValueLeft`, `featureValueRight`, `oneLinerText` | Text for advance/one-liner appearances |
| `searchString` | Content-anchored placement: `searchText`, `position`, `offset`, `width`, `height` (overrides `pageCoordinates`) |

### `getSignedPdf`

Adds: `responseXML` (Base64 of the `eSignResponse` POSTed by the gateway) and `preSignedTempFile` (the temp-file path returned by `getGatewayParam`).

### `getTransactionStatus`

Adds: `transactionID`.

### `performBankKYC`

Adds: `transactionID`, `IFSCCode`, `bankName`, `accountNumber`, `bankKYCURL`, and a `userInfo` object (`name`, `mobile`, `email`, `address`, `stateProvince`, `country`, `postalCode`, `dateOfBirth`, `gender`, `pan`, `aadhaar`, `photoFormat`, `photoBase64`).

---

## Output JSON

All methods write a single `CLIOutput` object:

| Field | Description |
|-------|-------------|
| `transactionID` | Transaction identifier |
| `gatewayParameter` | (getGatewayParam) value to POST to the gateway |
| `preSignedTempFile` | (getGatewayParam) temp-file path to pass to `getSignedPdf` |
| `requestXML`, `responseXML` | Underlying gateway XML |
| `status` | `1` = success, `0` = failure |
| `responseCode` | Gateway response code |
| `errorCode`, `errorMessage` | Error details when `status` is `0` |
| `returnValues` | Array of signed documents (`signedDocument` base64, `documentHash`, `docInfo`, `docURL`, `docId`, `status`, `errorCode`, `errorMessage`, `isHash`) |

---

## License

GNU Affero General Public License v3.0 (AGPL-3.0). See [LICENSE.txt](LICENSE.txt).

The bundled eSign library is also AGPL-3.0 (driven by its embedded iText); commercial use requires a separate iText commercial license.

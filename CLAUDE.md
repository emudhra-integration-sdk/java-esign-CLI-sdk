# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this is

A thin command-line wrapper around eMudhra's open-source `eSignASPLibrary` (the `com.emudhra.esign.*` JAR, **v5.5**, in `lib/eSignASPLibrary5_5.jar`). It does almost no logic of its own: it reads a JSON request file, maps it onto the eSign library's API, calls one library method, and writes the library's response as a JSON file. Almost all real signing behavior lives in the bundled library, not here.

**No licence file is required.** Earlier (proprietary) builds passed a `licenceFilePath` as the first constructor argument. The open-source library replaced that with explicit `ASPID` + gateway URLs, so the input JSON now provides `aspId`, `eSignURL` (V3/PAN gateway), and `eSignURLV2` (V2/Aadhaar gateway) instead of `licenceFilePath`. `BaseInput` no longer has a `licenceFilePath` field.

## Actual CLI contract (the README is aspirational — trust this instead)

The real entry point (`ESignASPLibraryCLI.main`) accepts exactly three switches; it does **not** implement the `sign`/`gateway`/`status`/`getsigned`/`localsign`/`batch` subcommands or `--flag` options described in README.md.

```
java -jar eSignASPLibraryCLI.jar -method <METHOD> -input <input.json> -output <output.json>
```

`-method`, `-input`, and `-output` are all mandatory (validated up front in `main`). `-method` is matched case-insensitively in `processRequest`. Supported methods:

| `-method` value      | Request DTO (`src/request/`) | Library call |
|----------------------|------------------------------|--------------|
| `GETGATEWAYPARAM`    | `GetGatewayParamInput`       | `eSign.getGatewayParameter(...)` — `eSignType`/`authMode` are converted to the `eSign.eSignAPIVersion`/`eSign.AuthMode` enums inside the DTO getters |
| `GETSIGNEDPDF`       | `GetSignedDocInput`          | `eSign.getSigedDocument(...)` (response XML is Base64-decoded first) |
| `GETTRANSACTIONSTATUS`| `GetStatusInput`            | `eSign.getStatus(...)` |
| `PERFORMBANKKYC`     | `BankKYCRequest`             | `eSign.performBankKYC(...)` — requires a `bankKYCURL` field in the input JSON |

Sample input/output JSON for each method live in `Resources/inputs/` and `Resources/outputs/` — use these as the schema reference (note: some are multi-MB because they embed Base64 PDFs/certs).

## Build

NetBeans + Apache Ant project (`build.xml` just imports `nbproject/build-impl.xml`). Java source/target is **1.8**.

```
ant clean        # remove build/ and dist/
ant jar          # compile + produce dist/eSignASPLibraryCLI.jar
ant compile      # compile only, to build/classes/
```

### Dependencies live in `lib/` (relative paths)

All compile/runtime JARs are checked into `lib/` and referenced by **relative** paths in `nbproject/project.properties` (`javac.classpath` = `lib/<jar>` entries). No machine-specific absolute paths, no licence file. The set:

- `eSignASPLibrary5_5.jar` — the core open-source eSign library (`com.emudhra.esign.*`, `org.emcastle.util.encoders.Base64`). Everything depends on this. Rebuild it from the `java-esign-sdk` repo and drop the new JAR into `lib/` to upgrade.
- `gson-2.10.1.jar` — JSON serialization (`com.google.gson`)
- `batik-all-1.13.jar`, `xmlgraphics-commons-2.4.jar`, `org.w3c.dom.svg-1.1.0.jar` — SVG/image appearance rendering
- `xmlsec-2.3.0.jar`, `woodstox-core-5.2.1.jar`, `stax2-api-4.2.jar` — XML signing/parsing
- `commons-io-2.4.jar`, `slf4j-api-1.7.32.jar`, `slf4j-simple-1.7.32.jar` — IO + logging

If Ant is unavailable, the project compiles directly: `javac -encoding UTF-8 -cp "$(printf '%s;' lib/*.jar)" -d build/classes @<(find src -name '*.java')` (use `;` as the classpath separator on Windows). The built `dist/eSignASPLibraryCLI.jar` has a `Class-Path` manifest entry pointing at `lib/`, so keep `lib/` next to the jar when running.

## Tests

`src/esignasplibrarycli/eSignAspLibraryCLITest.java` is **not** a JUnit test — it's a manual harness with a `test()` method (and embedded sample data) that is currently disconnected: the `-test` switch that invoked it is commented out in `main`. `ant test` exists (NetBeans default) but there are no JUnit tests wired up. To exercise the code, run the jar against the sample files in `Resources/`.

## Code layout

- `src/esignasplibrarycli/` — entry point and arg handling
  - `ESignASPLibraryCLI.java` — `main` + `processRequest` (the method switch) + `initLibrary` (builds the shared `eSign` object from `BaseInput` credentials/proxy fields). This is where you add a new method or change how the library is called.
  - `CliArgs.java` — minimal hand-rolled switch parser (`-key value`); no external CLI library.
  - `eSignInputsFeatureHandler.java` — maps `SignInput.appearanceType` strings (`StandardSignature`, `SignatureImage`, `advanceSignature`, `OneLiner`, `ColoredGraphic`, `BackgroundImage`) onto `eSignInputBuilder` configurations. This is the only file with branching logic of substance; edit it to change signature appearance behavior. Presence of `searchString` (ContentSearch) switches a signature to content-anchored placement vs. fixed `pageCoordinates`.
- `src/request/` — plain Gson DTOs for input JSON. `BaseInput` holds the shared library-init fields (`aspId`, `eSignURL`/`eSignURLV2` gateway URLs, PFX path/password/alias, proxy, session timeout); each method's input class extends it.
- `src/response/` — `CLIOutput` (the unified output wrapper, built from the library's `eSignServiceReturn`) and `RetDoc`.

## Conventions / gotchas

- Data flow is uniform: deserialize input JSON → `initLibrary(req)` → one library call → wrap result in `new CLIOutput(serviceReturn, "<METHOD>")` → `gson.toJson` to the output file. Follow this shape when adding a method.
- Gson is built with `disableHtmlEscaping()` — preserve this (output contains URLs/XML that must not be HTML-escaped).
- On success the program prints `Successful`; on `IOException`/`IllegalArgumentException` it prints the stack trace plus `Failure`. The README's documented numeric exit codes are not implemented.
- Credentials (PFX password, proxy password) arrive in plaintext inside the input JSON files — these files are sensitive.
- Licensed under AGPL-3.0 (`LICENSE.txt`).

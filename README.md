# eSign Library CLI Kit (Java)

A command-line interface (CLI) tool for the eSign Library, providing easy-to-use commands for PDF digital signature operations from the terminal.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
- [Building from Source](#building-from-source)
- [Quick Start](#quick-start)
- [CLI Commands](#cli-commands)
- [Configuration](#configuration)
- [Examples](#examples)
- [License](#license)
- [Contributing](#contributing)

---

## Overview

The eSign Library CLI Kit is a command-line wrapper around the [eSign Library](https://github.com/YOUR_USERNAME/Java_eSignLibKit), enabling developers and system administrators to integrate PDF digital signing capabilities into scripts, automation workflows, and CI/CD pipelines.

### What Can You Do?

- ✅ Sign PDF documents from the command line
- ✅ Generate gateway parameters for eSign workflows
- ✅ Check transaction status
- ✅ Retrieve signed documents
- ✅ Integrate with shell scripts and automation tools
- ✅ Batch process multiple documents

---

## Features

### Core Capabilities
- **Command-Line PDF Signing**: Sign PDFs directly from terminal
- **Multi-factor Authentication Support**:
  - OTP (One-Time Password)
  - Fingerprint
  - IRIS recognition
  - Face recognition
- **Flexible Signature Options**:
  - Standard signatures
  - Image-based signatures
  - Custom positioning (9-point grid)
  - Page-level control (All, Even, Odd, First, Last, specific pages)
- **Transaction Management**:
  - Generate gateway parameters
  - Check signing status
  - Retrieve signed documents
- **Scriptable & Automatable**:
  - JSON-based input/output
  - Exit codes for script integration
  - Batch processing support

---

## Requirements

### Runtime Requirements
- **Java**: JDK/JRE 1.8 or higher
- **Operating System**: Windows, Linux, macOS
- **eSign Library**: Uses eSignASPLibrary JAR internally

### Build Requirements
- **JDK**: 1.8 or higher
- **Apache Ant**: 1.9+ (for building from source)
- **NetBeans**: 8.0+ (optional, for IDE integration)

---

## Installation

### Option 1: Use Pre-built JAR

1. Download the latest `eSignASPLibraryCLI.jar` from the releases page
2. Place it in your desired location
3. Run the JAR:
   ```bash
   java -jar eSignASPLibraryCLI.jar --help
   ```

### Option 2: Build from Source

See [Building from Source](#building-from-source) below.

---

## Building from Source

### Using Apache Ant (Recommended)

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd Java_eSignLibCLIKit
   ```

2. **Build the project**:
   ```bash
   ant clean
   ant jar
   ```

3. **Locate the JAR**:
   The compiled JAR will be in `/dist/eSignASPLibraryCLI.jar`

### Using NetBeans IDE

1. Open NetBeans
2. File → Open Project
3. Select the `Java_eSignLibCLIKit` folder
4. Right-click → Clean and Build
5. JAR will be in `/dist` folder

---

## Quick Start

### Basic Command Structure

```bash
java -jar eSignASPLibraryCLI.jar <command> <options>
```

### Simple Signing Example

1. **Create a configuration JSON file** (`config.json`):
   ```json
   {
     "aspId": "YOUR_ASP_ID",
     "gatewayUrl": "https://gateway.example.com",
     "responseUrl": "https://yourapp.com/callback",
     "userEmail": "user@example.com",
     "userName": "John Doe",
     "userPhone": "9876543210",
     "pdfPath": "/path/to/document.pdf",
     "authMode": "OTP",
     "coordinates": "BOTTOMRIGHT"
   }
   ```

2. **Run the signing command**:
   ```bash
   java -jar eSignASPLibraryCLI.jar sign --config config.json
   ```

3. **Check the output**:
   The CLI will output JSON with gateway URL and transaction details.

---

## CLI Commands

### 1. Generate Gateway Parameters

Generate parameters to redirect user to eSign gateway:

```bash
java -jar eSignASPLibraryCLI.jar gateway \
  --aspid YOUR_ASP_ID \
  --gateway-url https://gateway.example.com \
  --response-url https://yourapp.com/callback \
  --email user@example.com \
  --name "John Doe" \
  --phone 9876543210 \
  --pdf document.pdf \
  --auth-mode OTP \
  --position BOTTOMRIGHT
```

**Output**:
```json
{
  "returnCode": "1",
  "gatewayURL": "https://gateway.example.com/sign?token=...",
  "responseURL": "https://yourapp.com/callback",
  "transactionId": "TXN123456",
  "message": "Success"
}
```

### 2. Check Transaction Status

Check the status of a signing transaction:

```bash
java -jar eSignASPLibraryCLI.jar status \
  --aspid YOUR_ASP_ID \
  --txn-id TXN123456 \
  --gateway-url https://gateway.example.com
```

**Output**:
```json
{
  "returnCode": "1",
  "status": "COMPLETED",
  "transactionId": "TXN123456",
  "message": "Document signed successfully"
}
```

### 3. Retrieve Signed Document

Get the signed PDF document:

```bash
java -jar eSignASPLibraryCLI.jar getsigned \
  --aspid YOUR_ASP_ID \
  --txn-id TXN123456 \
  --gateway-url https://gateway.example.com \
  --output signed_document.pdf
```

### 4. Direct Signing (Local Certificate)

Sign a document using a local PKCS12 certificate:

```bash
java -jar eSignASPLibraryCLI.jar localsign \
  --pdf document.pdf \
  --cert certificate.p12 \
  --password YOUR_CERT_PASSWORD \
  --output signed_document.pdf \
  --position BOTTOMRIGHT
```

### 5. Batch Processing

Process multiple documents from a batch file:

```bash
java -jar eSignASPLibraryCLI.jar batch --file documents.txt
```

**documents.txt format**:
```
/path/to/doc1.pdf,TXN001,user1@example.com
/path/to/doc2.pdf,TXN002,user2@example.com
/path/to/doc3.pdf,TXN003,user3@example.com
```

---

## Configuration

### Configuration File Format

Create a JSON configuration file for easier command execution:

```json
{
  "aspId": "YOUR_ASP_ID",
  "gatewayUrl": "https://gateway.example.com",
  "responseUrl": "https://yourapp.com/callback",
  "timeout": 30000,
  "proxy": {
    "host": "proxy.company.com",
    "port": 8080,
    "username": "proxyuser",
    "password": "proxypass"
  },
  "signing": {
    "authMode": "OTP",
    "appearanceType": "STANDARD",
    "coordinates": "BOTTOMRIGHT",
    "pageNo": "ALL"
  }
}
```

**Usage**:
```bash
java -jar eSignASPLibraryCLI.jar gateway --config config.json --pdf document.pdf
```

### Environment Variables

Set common values using environment variables:

```bash
export ESIGN_ASP_ID="YOUR_ASP_ID"
export ESIGN_GATEWAY_URL="https://gateway.example.com"
export ESIGN_RESPONSE_URL="https://yourapp.com/callback"

java -jar eSignASPLibraryCLI.jar gateway --pdf document.pdf
```

---

## Examples

### Example 1: Automated Document Signing Script

```bash
#!/bin/bash
# sign_documents.sh

ASPID="YOUR_ASP_ID"
GATEWAY="https://gateway.example.com"
RESPONSE="https://yourapp.com/callback"

for pdf in /documents/*.pdf; do
  echo "Signing: $pdf"

  result=$(java -jar eSignASPLibraryCLI.jar gateway \
    --aspid "$ASPID" \
    --gateway-url "$GATEWAY" \
    --response-url "$RESPONSE" \
    --pdf "$pdf" \
    --email "user@example.com" \
    --auth-mode OTP)

  txnId=$(echo "$result" | jq -r '.transactionId')
  echo "Transaction ID: $txnId"

  # Wait for signing
  sleep 30

  # Retrieve signed document
  java -jar eSignASPLibraryCLI.jar getsigned \
    --aspid "$ASPID" \
    --txn-id "$txnId" \
    --gateway-url "$GATEWAY" \
    --output "/signed/$(basename $pdf)"

  echo "Signed document saved"
done
```

### Example 2: CI/CD Integration

```yaml
# .github/workflows/sign-release.yml
name: Sign Release Documents

on:
  release:
    types: [published]

jobs:
  sign:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3

      - name: Set up JDK
        uses: actions/setup-java@v3
        with:
          java-version: '11'

      - name: Download eSign CLI
        run: |
          wget https://github.com/YOUR_USERNAME/Java_eSignLibCLIKit/releases/latest/download/eSignASPLibraryCLI.jar

      - name: Sign release document
        env:
          ASPID: ${{ secrets.ESIGN_ASP_ID }}
          GATEWAY: ${{ secrets.ESIGN_GATEWAY_URL }}
        run: |
          java -jar eSignASPLibraryCLI.jar localsign \
            --pdf release-notes.pdf \
            --cert ${{ secrets.SIGNING_CERT }} \
            --password ${{ secrets.CERT_PASSWORD }} \
            --output signed-release-notes.pdf

      - name: Upload signed document
        uses: actions/upload-artifact@v3
        with:
          name: signed-release-notes
          path: signed-release-notes.pdf
```

### Example 3: Docker Integration

```dockerfile
FROM openjdk:11-jre-slim

# Install eSign CLI
COPY eSignASPLibraryCLI.jar /app/esign-cli.jar

# Set up entry point
ENTRYPOINT ["java", "-jar", "/app/esign-cli.jar"]

# Usage:
# docker run esign-cli gateway --pdf /docs/file.pdf --config /config/esign.json
```

---

## Command Reference

### Global Options

| Option | Description | Default |
|--------|-------------|---------|
| `--help`, `-h` | Show help message | - |
| `--version`, `-v` | Show version information | - |
| `--config <file>` | Load configuration from JSON file | - |
| `--verbose` | Enable verbose output | false |
| `--json` | Output in JSON format | true |

### Common Options

| Option | Description | Required |
|--------|-------------|----------|
| `--aspid <id>` | Application Service Provider ID | Yes* |
| `--gateway-url <url>` | eSign gateway URL | Yes* |
| `--response-url <url>` | Callback URL | Yes* |
| `--pdf <file>` | Path to PDF document | Yes |
| `--email <email>` | User email address | Yes |

*Can be set via config file or environment variables

---

## Exit Codes

| Code | Meaning |
|------|---------|
| 0 | Success |
| 1 | General error |
| 2 | Invalid arguments |
| 3 | File not found |
| 4 | Network error |
| 5 | Signing failed |
| 6 | Transaction not found |

**Usage in scripts**:
```bash
java -jar eSignASPLibraryCLI.jar gateway --pdf document.pdf
if [ $? -eq 0 ]; then
  echo "Success!"
else
  echo "Failed with exit code $?"
fi
```

---

## Troubleshooting

### Common Issues

**Issue**: `ClassNotFoundException` when running
**Solution**: Ensure you're using the complete JAR with dependencies:
```bash
java -jar eSignASPLibraryCLI-with-dependencies.jar
```

**Issue**: Connection timeout
**Solution**: Increase timeout or check proxy settings:
```bash
java -jar eSignASPLibraryCLI.jar gateway --timeout 60000 ...
```

**Issue**: "Invalid ASPID" error
**Solution**: Verify ASPID is correctly configured

---

## License

This project is licensed under the **GNU Affero General Public License v3.0 (AGPL-3.0)**.

See [LICENSE.txt](LICENSE.txt) for full license text.

---

## Dependencies

This CLI tool depends on:
- [eSign Library (Java)](https://github.com/YOUR_USERNAME/Java_eSignLibKit) - Core signing functionality
- JSON processing libraries
- Command-line argument parsing utilities

---

## Contributing

We welcome contributions! See [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines.

---

## Support

For issues or questions:
- GitHub Issues: Report bugs and feature requests
- GitHub Discussions: Ask questions
- Security Issues: Email [security@example.com]

---

## Changelog

See [CHANGELOG.md](CHANGELOG.md) for version history.

---

**Built with Java | Command-Line Power for Digital Signatures**

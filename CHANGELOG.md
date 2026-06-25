# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

---

## [1.1.0] - 2026-06-26

### Changed
- Migrated to the open-source eSign library (`eSignASPLibrary5_5.jar`). **No licence file is required** — the library is now configured with `aspId`, `eSignURL` (V3) and `eSignURLV2` (V2) in the input JSON instead of a `licenceFilePath`.
- Bundled all compile/runtime dependencies into `lib/` and switched `nbproject/project.properties` to relative classpath entries (removed the original author's hard-coded absolute paths).
- `performBankKYC` input now requires a `bankKYCURL` field (new library signature).
- Sample input JSONs in `Resources/inputs/` updated to the licence-free configuration.

### Added
- GitHub Actions workflow that builds the project and publishes the JAR and its dependencies to GitHub Releases (with release notes and the latest changelog section) on every push to `main`.
- `NOTICE` file and an "Important Notice" disclaimer in the README: the CLI and any published JAR are provided "AS IS"; eMudhra's responsibility is limited to the eSign API and gateway services.

---

## [1.0.0] - 2025-12-24

### Initial Open Source Release

This is the first public release of the eSign Library CLI Kit for Java.

#### Added
- **Command-Line Interface**:
  - Gateway parameter generation command
  - Transaction status checking command
  - Signed document retrieval command
  - Local signing with PKCS12 certificates
  - Batch processing support

- **Input/Output Formats**:
  - JSON-based configuration files
  - JSON output for easy parsing
  - Command-line argument parsing
  - Environment variable support

- **Features**:
  - Multi-factor authentication support (OTP, Fingerprint, IRIS, Face)
  - Flexible signature positioning (9-point grid system)
  - Page-level signing control
  - Proxy server configuration support
  - Verbose logging mode

- **Automation Support**:
  - Shell script integration
  - CI/CD pipeline compatibility
  - Docker container support
  - Exit codes for script error handling
  - Batch file processing

- **Documentation**:
  - Comprehensive README with examples
  - CI/CD integration examples
  - Docker usage examples
  - Automation script examples

#### Platform Support
- Java 1.8+
- Windows, Linux, macOS
- Any platform supporting Java runtime

#### Dependencies
- eSign Library (Java) - Core signing functionality
- JSON processing library
- Apache Commons CLI

---

## [Unreleased]

### Planned Features
- Interactive mode for guided signing
- Configuration wizard
- Template support for common workflows
- Multiple output formats (XML, CSV)
- Progress bars for batch operations
- Retry logic for network failures
- Certificate validation utilities
- Digital signature verification command

### Improvements Planned
- Performance optimizations for batch processing
- Better error messages and diagnostics
- Auto-completion support for bash/zsh
- Man page documentation
- Windows PowerShell integration
- Logging to file option

---

## Release Notes

### Version 1.0.0 Notes

This release provides a complete command-line interface for the eSign Library. Key highlights:

1. **License**: Released under AGPL-3.0 license
2. **Documentation**: Comprehensive examples for automation and integration
3. **Compatibility**: Cross-platform support for any Java-enabled system
4. **Automation-Ready**: Designed for scripts, CI/CD, and batch processing

### Use Cases

**Development & Testing**:
- Quick testing of eSign integration without writing code
- Prototyping signing workflows
- Debugging signature operations

**Production Automation**:
- Batch signing of documents
- CI/CD pipeline integration
- Scheduled document processing
- Server-side automation scripts

**System Administration**:
- One-off document signing tasks
- Backup and archive signing
- Compliance document processing

---

## Migration Guide

### From Manual Java Code to CLI

**Before** (Java code):
```java
eSignInput input = new eSignInputBuilder()
    .seteSignSettings(settings)
    .setUserInfo(userInfo)
    .setPDFBase64(pdfBase64)
    .build();

eSign esign = new eSign();
eSignServiceReturn result = esign.GenerateGatewayParameters(input);
```

**After** (CLI):
```bash
java -jar eSignASPLibraryCLI.jar gateway \
  --config settings.json \
  --pdf document.pdf \
  --email user@example.com
```

---

## Known Issues

See [SECURITY.md](SECURITY.md) for security considerations:
- Review SSL/TLS certificate validation
- Use secure credential storage (environment variables, config files with restricted permissions)
- Never commit credentials to version control

---

## Support

For support with this release:
- GitHub Issues: Report bugs and feature requests
- GitHub Discussions: Ask questions and share automation examples
- Security Issues: Email [security@example.com]

---

## Acknowledgments

Special thanks to:
- The eSign Library team for the core signing functionality
- Apache Commons CLI for command-line parsing
- All contributors who helped shape this CLI tool

---

## Versioning

This project uses [Semantic Versioning](https://semver.org/):
- **MAJOR**: Incompatible API/CLI changes
- **MINOR**: New functionality (backward compatible)
- **PATCH**: Bug fixes (backward compatible)

---

**Last Updated**: 2025-12-24

<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# CppFastCoding Changelog

## [Unreleased]

### Added

- Initial scaffold created
  from [IntelliJ Platform Plugin Template](https://github.com/JetBrains/intellij-platform-plugin-template)

## [1.0.0] - 2024/04/18

### Added

- Basic function: Run and manage multi testcase.

## [1.0.1] - 2024/04/01

### Added

- Setting compile standard.
- Setting maximum waiting time.
- Different TestCases for different cpp file: The plugin window will store your inputs for each file and automatically
  change when you select another file.
- Expandable and collapsible testcase windows.

## [1.0.2] - 2024/04/10

### Changed

- Bug fix.
- Buttons appearance.
- Input/output and expansion status will be recorded.

## [1.0.3] - 2024/04/16

### Added

- Expected output added. Now you will get "WrongAnswer" verdict if your output is different from the expected output.
  The comparison method will ignore characters like '\n' and '\000'.

## [1.0.4] - 2024/05/17

### Added

- Button icon.

### Changed

- Bug fix.

## [1.0.4-hotfix0528] - 2024/05/28

### Fixed

- The text area is displayed in a incorrect size after switching files.

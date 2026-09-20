## 🛠️ Changes

- Fix the value of a conf being lost when the config object is replaced, the value is now kept by the registry
- Fix the font of the MineConf windows: search the chinese fonts of macOS, check that the found font can really
  render the language and only use it for the windows of MineConf
- Fix the crash of the font atlas when a system font is merged
- Add the font tests for macOS

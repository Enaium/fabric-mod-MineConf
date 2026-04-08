# MineConf

MineConf is a powerful configuration library for Minecraft Fabric mods, providing a simple and intuitive API along with
a user-friendly graphical interface to help mod developers easily manage and maintain configuration files.

[![Version](https://img.shields.io/github/v/tag/Enaium/fabric-mod-MineConf?label=version&style=flat-square&logo=github)](https://github.com/Enaium/fabric-mod-MineConf/releases)
[![CurseForge Downloads](https://img.shields.io/curseforge/dt/1486586?style=flat-square&logo=curseforge)](https://www.curseforge.com/minecraft/mc-mods/mineconf)
[![Modrinth Downloads](https://img.shields.io/modrinth/dt/NfULUoHI?style=flat-square&logo=modrinth)](https://modrinth.com/mod/MineConf)

## 🚀 Features

- **Multiple Configuration Types**: Supports Literal, Number, Vec2, Vec3, Vec4, Option, Enum, Collection, Multimap, and
  more
- **Flexible API**: Uses a fluent builder pattern to create configuration entries
- **Graphical Interface**: Provides an intuitive ImGui-based GUI for users to adjust settings(Required [fabric-gui-imgui](https://github.com/Enaium/fabric-mod-ImGui))
- **Command-Line Interface**: Supports viewing and modifying configurations via commands
- **JSON Serialization**: Automatically serializes configurations to JSON format for easy storage and retrieval
- **Service Loader**: Integrates with other mods using the Service Loader pattern
- **Multi-Version Support**: Compatible with multiple Minecraft versions (1.7.10 to 26.1)

## 📦 Supported Configuration Types

| Type       | Description                                           | Example                             |
|------------|-------------------------------------------------------|-------------------------------------|
| Literal    | Basic type configuration                              | `String`, `Boolean`, `Int`, `Float` |
| Number     | Numeric configuration with range limits and step      | `Int`, `Double`, `Float`            |
| Vec2       | 2D vector configuration                               | `x`, `y` coordinates                |
| Vec3       | 3D vector configuration                               | `x`, `y`, `z` coordinates           |
| Vec4       | 4D vector configuration                               | `x`, `y`, `z`, `w` coordinates      |
| Option     | Option configuration (select from predefined options) | Dropdown selection                  |
| Enum       | Enum type configuration                               | Java/Kotlin enums                   |
| Collection | Collection type configuration                         | Lists, arrays                       |
| Multimap   | Multi-map type configuration                          | Key-value pair collections          |

## 🎯 Quick Start

### 1. Add Dependency

Add MineConf as a dependency in your mod's `build.gradle.kts` file:

```kotlin
dependencies {
    modImplementation("cn.enaium:mineconf:@version@")
}
```

26.1+:

```kotlin
dependencies {
    implementation("cn.enaium:mineconf:@version@")
}
```

Common api:

```kotlin
dependencies {
    implementation("cn.enaium:mineconf-api:@version@")
}
```

1.7.10-1.12.2:

```kotlin
repositories {
    maven("https://jitpack.io")
}
```

### 2. Create Configuration Class

```kotlin
package cn.enaium.mineconf.core.config

object MineConfConfig {
    var fontScale = ConfBuilder.create()
        .id("font_scale")
        .name("Font Scale")
        .description("Font scale of the imgui")
        .literal<Float>().build(2f)

    var playerSpeed = ConfBuilder.create()
        .id("player_speed")
        .name("Player Speed")
        .description("Speed of the player")
        .number<Double>()
        .range { it.min(0.5).max(5.0) }
        .step(0.1)
        .build(1.0)

    var gameDifficulty = ConfBuilder.create()
        .id("game_difficulty")
        .name("Game Difficulty")
        .description("Difficulty level of the game")
        .enumeration<Difficulty>()
        .type(Difficulty::class.java)
        .build(Difficulty.NORMAL)
}

enum class Difficulty {
    PEACEFUL, EASY, NORMAL, HARD
}
```

### 3. Implement Service Interface

```kotlin
package cn.enaium.mineconf.core.config

class MineConfServiceImpl : MineConfService {
    override fun conf(): MineConf {
        return MineConf("mineconf", "MineConf", MineConfConfig)
    }
}
```

### 4. Register Service

Create a new file `META-INF/services/cn.enaium.mineconf.core.MineConfService` and add the following content:

```
cn.enaium.mineconf.core.config.MineConfServiceImpl
```

## 📚 Detailed Usage

### Configuration Builder

MineConf uses a fluent builder pattern to create configuration entries:

```kotlin
val config = ConfBuilder.create()
    .id("config_id")
    .name("Config Name")
    .description("Config description")
    .literal<String>().build("default value")
```

### Number Configuration

```kotlin
val numberConfig = ConfBuilder.create()
    .id("number_config")
    .name("Number Config")
    .description("A number with range and step")
    .number<Int>()
    .range { it.min(0).max(100) }
    .step(5)
    .build(50)
```

### Vector Configuration

```kotlin
val vec3Config = ConfBuilder.create()
    .id("vec3_config")
    .name("Vec3 Config")
    .description("A 3D vector")
    .vec3<Double>()
    .rangeX { it.min(-10.0).max(10.0) }
    .rangeY { it.min(-10.0).max(10.0) }
    .rangeZ { it.min(-10.0).max(10.0) }
    .stepX(0.5)
    .stepY(0.5)
    .stepZ(0.5)
    .build(0.0, 0.0, 0.0)
```

### Option Configuration

```kotlin
val optionConfig = ConfBuilder.create()
    .id("option_config")
    .name("Option Config")
    .description("Select an option")
    .option<String>()
    .options(listOf("Option 1", "Option 2", "Option 3"))
    .build("Option 1")
```

### Collection Configuration

```kotlin
val collectionConfig = ConfBuilder.create()
    .id("collection_config")
    .name("Collection Config")
    .description("A collection of strings")
    .collection<String>()
    .options(listOf("Item 1", "Item 2", "Item 3"))
    .converter { it }
    .build(mutableListOf("Item 1"))
```

## 🎮 Command-Line Interface

MineConf provides a complete command-line interface for viewing and modifying configurations:

| Command                                             | Description                                                         |
|-----------------------------------------------------|---------------------------------------------------------------------|
| `/mineconf screen`                                  | Open the configuration interface for all mods                       |
| `/mineconf screen <mod_id>`                         | Open the configuration interface for a specific mod                 |
| `/mineconf get <mod_id> <conf_id>`                  | Get a configuration basic info                                      |
| `/mineconf get <mod_id> <conf_id> value`            | Get a configuration value info                                      |
| `/mineconf set <mod_id> <conf_id> <value>`          | Set a value for literal type configurations                         |
| `/mineconf append <mod_id> <conf_id> <value>`       | Append an element to a collection type configuration                |
| `/mineconf append <mod_id> <conf_id> <key> <value>` | Append a key-value pair to a multimap type configuration            |
| `/mineconf remove <mod_id> <conf_id> <index>`       | Remove an element from a collection type configuration              |
| `/mineconf remove <mod_id> <conf_id> <key>`         | Remove a key from a multimap type configuration                     |
| `/mineconf remove <mod_id> <conf_id> <key> <value>` | Remove a specific key-value pair from a multimap type configuration |
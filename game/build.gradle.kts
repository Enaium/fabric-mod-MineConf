import net.fabricmc.loom.build.nesting.NestableJarGenerationTask
import org.gradle.util.internal.VersionNumber

plugins {
    id("cn.enaium.fabric-multi-game")
}

fmg {
    common.set(project(":core"))
}

val fabricImGui = libs.versions.fabricImGui.get()

subprojects {
    apply(plugin = "mod-publish")

    val includeAndExpose: Configuration by configurations.creating {
        exclude(module = "kotlin-stdlib")
        exclude(module = "kotlin-reflect")
    }

    configurations {
        named("include") {
            extendsFrom(includeAndExpose)
        }
    }

    val disableObfuscation = properties.getOrDefault("fabric.loom.disableObfuscation", false).toString().toBoolean()

    val minecraftVersion = properties["minecraft.version"]
    val imgui = "$minecraftVersion-${fabricImGui}"

    if (VersionNumber.parse(minecraftVersion.toString()) > VersionNumber.parse("1.7.10")) {
        dependencies.add(
            if (disableObfuscation) "implementation" else "modImplementation",
            "cn.enaium:fabric-gui-imgui:${imgui}"
        )
    }

    afterEvaluate {
        dependencies {
            includeAndExpose(libs.jackson)

            if (VersionNumber.parse(minecraftVersion.toString()) < VersionNumber.parse("1.14")) {
                includeAndExpose(libs.brigadier)
            }
        }

        tasks.named<NestableJarGenerationTask>("processIncludeJars") {
            from(includeAndExpose)
        }
    }
}
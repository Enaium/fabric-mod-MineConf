import me.modmuss50.mpp.PublishModTask
import org.gradle.util.internal.VersionNumber

plugins {
    java
    id("me.modmuss50.mod-publish-plugin")
    id("com.vanniktech.maven.publish")
}

afterEvaluate {
    publishMods {
        val disableObfuscation = properties.getOrDefault("fabric.loom.disableObfuscation", false).toString().toBoolean()
        file = tasks.named<AbstractArchiveTask>(if (disableObfuscation) "jar" else "remapJar").get().archiveFile.get()
        type = STABLE
        displayName = "MineConf ${project.version}"
        changelog = rootProject.file("changelog.md").readText(Charsets.UTF_8)
        modLoaders.add("fabric")

        val modern = VersionNumber.parse(properties["minecraft.version"].toString()) >= VersionNumber.parse("1.14")

        curseforge {
            projectId = "1486586"
            accessToken = providers.gradleProperty("curseforge.token")
            minecraftVersions.add(property("minecraft.version").toString())
            requires("fabric-language-kotlin", if (modern) "fabric-api" else "legacy-fabric-api")
        }

        modrinth {
            projectId = "NfULUoHI"
            accessToken = providers.gradleProperty("modrinth.token")
            minecraftVersions.add(property("minecraft.version").toString())
            requires("fabric-language-kotlin", if (modern) "fabric-api" else "legacy-fabric-api")
        }

        github {
            repository = "Enaium/fabric-mod-MineConf"
            accessToken = providers.gradleProperty("github.token")
            commitish = "master"
        }

        tasks.withType<PublishModTask>().configureEach {
            dependsOn(tasks.named(if (disableObfuscation) "jar" else "remapJar"))
        }
    }
}

mavenPublishing {

    publishToMavenCentral(automaticRelease = true)

    signAllPublications()

    coordinates(
        groupId = project.group.toString(),
        artifactId = rootProject.name,
        version = project.version.toString()
    )

    pom {
        name = "MineConf"
        description = "Minecraft Config Library"
        url = "https://github.com/Enaium/fabric-mod-MineConf"
        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }
        developers {
            developer {
                name = "Enaium"
                url = "https://github.com/Enaium"
            }
        }
        scm {
            connection.set("scm:git:git://github.com/Enaium/fabric-mod-MineConf.git")
            developerConnection.set("scm:git:ssh://github.com/Enaium/fabric-mod-MineConf.git")
            url.set("https://github.com/Enaium/fabric-mod-MineConf")
        }
    }
}
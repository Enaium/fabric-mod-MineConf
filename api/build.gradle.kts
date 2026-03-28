plugins {
    java
    alias(libs.plugins.kotlin)
    alias(libs.plugins.maven)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

afterEvaluate {
    tasks.jar {
        from(project(":core").sourceSets.main.get().output.classesDirs)
    }

    tasks.named<Jar>("sourcesJar") {
        from(project(":core").sourceSets.main.get().kotlin.srcDirs)
    }
}


mavenPublishing {

    publishToMavenCentral(automaticRelease = true)

    signAllPublications()

    coordinates(
        groupId = project.group.toString(),
        artifactId = "${rootProject.name}-${project.name}",
        version = project.version.toString()
    )

    pom {
        name = "MineConfCore"
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
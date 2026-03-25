allprojects {
    group = "cn.enaium"
    version = rootProject.property("version").toString()

    repositories {
        mavenCentral()
        maven {
            name = "Fabric"
            url = uri("https://maven.fabricmc.net/")
        }
        maven {
            name = "Minecraft"
            url = uri("https://libraries.minecraft.net")
        }
        maven { url = uri("https://jitpack.io") }
    }
}
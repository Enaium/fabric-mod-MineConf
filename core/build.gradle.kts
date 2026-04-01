plugins {
    java
    alias(libs.plugins.kotlin)
}

dependencies {
    api(libs.jackson)
    implementation(libs.fabric.kotlin)
    implementation(libs.imgui.java.binding)
    implementation(libs.imgui.java.natives.windows)
    implementation(libs.imgui.java.natives.linux)
    implementation(libs.imgui.java.natives.macos)

    api(libs.brigadier)
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation(libs.imgui.java.app)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

kotlin {
    jvmToolchain(8)
}
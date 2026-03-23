plugins {
    java
    alias(libs.plugins.kotlin)
}

dependencies {
    api(libs.jackson)
    implementation(libs.fabric.kotlin)
    implementation(libs.imgui)
    implementation(libs.brigadier)
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
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
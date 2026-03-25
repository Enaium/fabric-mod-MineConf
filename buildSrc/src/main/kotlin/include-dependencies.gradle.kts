plugins {
    java
    id("fabric-loom")
}

val includeAndExpose: Configuration by configurations.creating {
    exclude(module = "kotlin-stdlib")
    exclude(module = "kotlin-reflect")
}

configurations {
    include {
        extendsFrom(includeAndExpose)
    }
    modApi {
        extendsFrom(includeAndExpose)
    }
}

dependencies {
    includeAndExpose("com.fasterxml.jackson.core:jackson-databind:2.21.1")
}
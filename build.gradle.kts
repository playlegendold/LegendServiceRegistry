group = "net.playlegend"
version = "1.0.0"

plugins {
    java
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "5.1.0"
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")
    apply(plugin = "com.github.johnrengelman.shadow")

    repositories {
        mavenCentral()
    }

    dependencies {
        compileOnly("org.projectlombok:lombok:1.18.10")
        annotationProcessor("org.projectlombok:lombok:1.18.10")
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_12
        targetCompatibility = JavaVersion.VERSION_12
        withSourcesJar()
    }

    tasks.withType<JavaCompile> { options.encoding = "UTF-8" }
}
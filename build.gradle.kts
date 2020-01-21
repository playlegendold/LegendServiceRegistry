import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

group = "net.playlegend"
version = "1.0.0"

plugins {
    java
    id("com.github.johnrengelman.shadow") version "4.0.4"
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "com.github.johnrengelman.shadow")

    repositories {
        mavenCentral()
    }

    dependencies {
        compileOnly("org.projectlombok:lombok:1.18.10")
        annotationProcessor("org.projectlombok:lombok:1.18.10")
        testImplementation("junit", "junit", "4.12")
    }

    configure<JavaPluginConvention> {
        sourceCompatibility = JavaVersion.VERSION_12
    }
}
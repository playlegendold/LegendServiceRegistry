group = "net.playlegend"
version = "1.0.0"

plugins {
    java
    `maven-publish`
    checkstyle
    id("com.github.johnrengelman.shadow") version "5.1.0"
    id("org.sonarqube") version "2.7"
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")
    apply(plugin = "checkstyle")
    apply(plugin = "com.github.johnrengelman.shadow")

    checkstyle {
        toolVersion = "8.30"
        config = project.resources.text.fromUri("https://static.playlegend.dev/checkstyle.xml")
    }

    repositories {
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://papermc.io/repo/repository/maven-public/")
        maven {
            url = uri("https://repository.playlegend.dev/legend")
            credentials {
                if (System.getenv("CI") != null) {
                    username = System.getenv("repositoryUser")
                    password = System.getenv("repositoryPassword")
                } else {
                    username = project.properties["repositoryUser"] as String?
                    password = project.properties["repositoryPassword"] as String?
                }
            }
        }
        mavenCentral()
        jcenter()
    }

    dependencies {
        compileOnly("org.projectlombok:lombok:1.18.12")
        annotationProcessor("org.projectlombok:lombok:1.18.12")
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_12
        targetCompatibility = JavaVersion.VERSION_12
    }

    tasks.withType<JavaCompile> { options.encoding = "UTF-8" }
}
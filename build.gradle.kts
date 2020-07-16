group = "net.playlegend"
version = "1.0.0"

plugins {
    java
    `maven-publish`
    checkstyle
    id("com.github.johnrengelman.shadow") version "5.1.0"
    id("com.gorylenko.gradle-git-properties") version "2.2.2"
}

tasks.create<Copy>("copyHooks") {
    from(file("./hooks/"))
    into(file("./.git/hooks/"))
}

tasks.getByPath("prepareKotlinBuildScriptModel").dependsOn("copyHooks")

subprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")
    apply(plugin = "checkstyle")
    apply(plugin = "com.github.johnrengelman.shadow")
    apply(plugin = "com.gorylenko.gradle-git-properties")

    checkstyle {
        toolVersion = "8.34"
        config = project.resources.text.fromUri("https://static.playlegend.net/checkstyle.xml")
    }

    gitProperties {
        gitPropertiesName = "git.properties"
        dateFormat = "yyyy-MM-dd'T'HH:mm:ss"
        keys = arrayOf("git.branch", "git.build.host", "git.build.version", "git.commit.id", "git.commit.id.abbrev",
                "git.commit.message.full", "git.commit.message.short", "git.commit.time", "git.commit.user.email",
                "git.commit.user.name", "git.remote.origin.url", "git.total.commit.count").toMutableList()
    }

    repositories {
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        mavenCentral()
        jcenter()
    }

    dependencies {
        implementation("org.jetbrains:annotations:16.0.2")
        compileOnly("org.projectlombok:lombok:1.18.12")
        annotationProcessor("org.projectlombok:lombok:1.18.12")
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    tasks.withType<JavaCompile> { options.encoding = "UTF-8" }
}
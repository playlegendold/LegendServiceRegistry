plugins {
    java
}

group = "net.playlegend"
version = "1.0.0"

repositories {
    maven("https://papermc.io/repo/repository/maven-public/")
    maven {
        url = uri("https://repository.playlegend.net/legend")
        credentials {
            if (System.getenv("CI") != null) {
                username = System.getenv("legendUser")
                password = System.getenv("legendPassword")
            } else {
                username = project.properties["legendUser"] as String?
                password = project.properties["legendPassword"] as String?
            }
        }
    }
    mavenCentral()
}

dependencies {
    compileOnly("net.playlegend:bewear-api:1.14.4-dev")
    implementation(project(":common"))
    testImplementation("junit", "junit", "4.12")
}
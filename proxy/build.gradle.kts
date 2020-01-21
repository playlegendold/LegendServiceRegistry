plugins {
    java
}

group = "net.playlegend"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":common"))
    testImplementation("junit", "junit", "4.12")
}
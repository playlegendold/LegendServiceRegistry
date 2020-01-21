plugins {
    java
}

group = "net.playlegend"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.10")
    annotationProcessor("org.projectlombok:lombok:1.18.10")
    testImplementation("junit", "junit", "4.12")
}
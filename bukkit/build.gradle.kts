plugins {
    java
}

group = "net.playlegend"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    compile(project(":common"))
    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_12
}
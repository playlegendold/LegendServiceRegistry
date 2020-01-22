plugins {
    java
}

group = "net.playlegend"
version = "1.0.0"

dependencies {
    compileOnly("io.github.waterfallmc:waterfall-api:1.15-SNAPSHOT")
    implementation(project(":legendserviceregistry-common"))
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_12
}
val branch: String? = System.getenv("BITBUCKET_BRANCH")
val tag: String? = System.getenv("BITBUCKET_TAG")

group = "net.playlegend"
version = if (System.getenv("CI") != null) {
    (branch ?: tag).toString()
} else {
    "dev"
}.replace("/", "-")

dependencies {
    compileOnly("io.github.waterfallmc:waterfall-api:1.15-SNAPSHOT")
    implementation(project(":legendserviceregistry-common"))
}

val tokens = mapOf("VERSION" to project.version)

tasks.withType<ProcessResources> {
    filesMatching("*.yml") {
        filter<org.apache.tools.ant.filters.ReplaceTokens>("tokens" to tokens)
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name.toLowerCase()
            version = project.version.toString()

            from(components["java"])
            artifact(tasks["shadowJar"])
        }
    }
    repositories {
        maven {
            credentials {
                username = System.getenv("legendUser")
                password = System.getenv("legendPassword")
            }
            url = uri(if (System.getenv("BITBUCKET_TAG") != null) {
                "https://repository.playlegend.dev/legend-release/"
            } else {
                "https://repository.playlegend.dev/legend-snapshots/"
            })
        }
    }
}
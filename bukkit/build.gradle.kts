val branch: String? = System.getenv("BITBUCKET_BRANCH")
val tag: String? = System.getenv("BITBUCKET_TAG")

group = "net.playlegend"
version = if (System.getenv("CI") != null) {
    (branch ?: tag).toString()
} else {
    "dev"
}.replace("/", "-")

dependencies {
    compileOnly("net.playlegend:bewear-api:1.15.1-dev")
    implementation(project(":legendserviceregistry-common"))
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
                "https://repository.playlegend.net/legend-release/"
            } else {
                "https://repository.playlegend.net/legend-snapshots/"
            })
        }
    }
}
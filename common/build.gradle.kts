val branch: String? = System.getenv("BITBUCKET_BRANCH")
val tag: String? = System.getenv("BITBUCKET_TAG")

group = "net.playlegend"
version = if (System.getenv("CI") != null) {
    (branch ?: tag).toString()
} else {
    "dev"
}.replace("/", "-")

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.10")
    annotationProcessor("org.projectlombok:lombok:1.18.10")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name.toLowerCase()
            version = project.version.toString()

            from(components["java"])
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
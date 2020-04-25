val branch: String? = System.getenv("GITHUB_REF")?.replace("refs/heads/", "")

group = "net.playlegend"
version = if (System.getenv("CI") != null) {
    branch.toString()
} else {
    "dev"
}.replace("/", "-")

tasks.register<Jar>("fatSources") {
    from(sourceSets["main"].allSource)
    archiveClassifier.set("sources")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name.toLowerCase()
            version = project.version.toString()

            from(components["java"])
            artifact(tasks["shadowJar"])
            artifact(tasks["fatSources"])
        }
    }
    repositories {
        maven {
            credentials {
                username = System.getenv("repositoryUser")
                password = System.getenv("repositoryPassword")
            }
            url = uri(if (System.getenv("BITBUCKET_TAG") != null) {
                "https://repository.playlegend.dev/legend-release/"
            } else {
                "https://repository.playlegend.dev/legend-snapshots/"
            })
        }
    }
}
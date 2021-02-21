val branch: String? = System.getenv("GITHUB_REF")
        ?.replace("refs/heads/", "")
        ?.replace("refs/tags/", "")

group = "net.playlegend"
version = if (System.getenv("CI") != null) {
    if (branch.equals("stage") || branch.equals("prod")
            || branch!!.matches(Regex("v\\d+[.]\\d+[.]\\d+"))) {
        branch.toString()
    } else {
        "$branch-SNAPSHOT"
    }
} else {
    "dev-SNAPSHOT"
}.replace("/", "-")

tasks.register<Jar>("fatSources") {
    from(sourceSets["main"].allSource)
    archiveClassifier.set("sources")
}

repositories {
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    api("com.destroystokyo.paper:paper-api:1.16.5-R0.1-SNAPSHOT")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name.toLowerCase()
            version = project.version.toString()

            artifact(tasks["jar"])
            artifact(tasks["javadocJar"])
            artifact(tasks["shadowJar"])
        }
    }
    repositories {
        maven {
            credentials {
                username = System.getenv("repositoryUser")
                password = System.getenv("repositoryPassword")
            }
            url = uri("https://repository.playlegend.net/artifactory/opensource/")
        }
    }
}
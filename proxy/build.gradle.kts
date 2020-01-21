val branch: String? = System.getenv("BITBUCKET_BRANCH")
val tag: String? = System.getenv("BITBUCKET_TAG")

group = "net.playlegend.legendserviceregistry"
version = if (System.getenv("CI") != null) {
    (branch ?: tag).toString()
} else {
    "dev"
}.replace("/", "-")

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
    implementation(project(":common"))
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
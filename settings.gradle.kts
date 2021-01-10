rootProject.name = "LegendServiceRegistry"
include("common")
include("proxy")
include("bukkit")

project(":common").name = "LegendServiceRegistry-Common"
project(":proxy").name = "LegendServiceRegistry-Proxy"
project(":bukkit").name = "LegendServiceRegistry-Bukkit"

plugins {
    id("com.gradle.enterprise").version("3.5.1")
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
    }
}

buildCache {
    local {
        isEnabled = false
    }
    remote(HttpBuildCache::class) {
        url = uri("https://repository.playlegend.net/artifactory/cache/")
        credentials {
            username = System.getenv("repositoryUser")
            password = System.getenv("repositoryPassword")
        }
        isPush = true;
    }
}
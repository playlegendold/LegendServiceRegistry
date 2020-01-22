rootProject.name = "legendserviceregistry"
include("common")
include("proxy")
include("bukkit")

project(":common").name = "legendserviceregistry-common"
project(":proxy").name = "legendserviceregistry-proxy"
project(":bukkit").name = "legendserviceregistry-bukkit"
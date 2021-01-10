rootProject.name = "LegendServiceRegistry"
include("common")
include("proxy")
include("bukkit")

project(":common").name = "LegendServiceRegistry-Common"
project(":proxy").name = "LegendServiceRegistry-Proxy"
project(":bukkit").name = "LegendServiceRegistry-Bukkit"
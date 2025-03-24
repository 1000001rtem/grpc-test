pluginManagement {
    plugins {
        kotlin("jvm") version "2.1.10"
    }
}
rootProject.name = "grpc-test"
include("api")
include("api:model")
include("api:client-starter")

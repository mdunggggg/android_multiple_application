pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Multi-Application"
include(
    ":apps",

    // Applications
    ":apps:learning_app",
    ":apps:todo_app",

    // Core
    ":core:core-network",
    ":core:core-service",
    ":core:core-util",
    ":core:core-ui",)

// App project directories
project(":apps:learning_app").projectDir = File(rootProject.projectDir, "./apps/learning_app")
project(":apps:todo_app").projectDir = File(rootProject.projectDir, "./apps/todo_app")

// Core project directories
project(":core:core-network").projectDir = File(rootProject.projectDir, "./core/core-network")
project(":core:core-service").projectDir = File(rootProject.projectDir, "./core/core-service")
project(":core:core-util").projectDir = File(rootProject.projectDir, "./core/core-util")
project(":core:core-ui").projectDir = File(rootProject.projectDir, "./core/core-ui")
include(":apps:jamendo_music")

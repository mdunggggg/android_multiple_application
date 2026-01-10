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
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        maven {
            url = uri("https://storage.googleapis.com/download.flutter.io")
        }
        maven {
            url = uri("https://jitpack.io")
        }
    }
}

rootProject.name = "Multi-Application"
include(
    ":apps",

    // Learning App and ToDo App
    ":apps:learning_app",
    ":apps:todo_app",

    // Jamendo Music App
    ":apps:jamendo_music",
    ":apps:jamendo_core_data",


    // Core
    ":core:core-network",
    ":core:core-service",
    ":core:core-util",
    ":core:core-ui",
)

// App project directories
project(":apps:learning_app").projectDir = File(rootProject.projectDir, "./apps/learning_app")
project(":apps:todo_app").projectDir = File(rootProject.projectDir, "./apps/todo_app")

// Jamendo Music app directory
project(":apps:jamendo_music").projectDir = File(rootProject.projectDir, "./apps/jamendo_music")
project(":apps:jamendo_core_data").projectDir = File(rootProject.projectDir, "./apps/jamendo_music/jamendo_core_data")

// Core project directories
project(":core:core-network").projectDir = File(rootProject.projectDir, "./core/core-network")
project(":core:core-service").projectDir = File(rootProject.projectDir, "./core/core-service")
project(":core:core-util").projectDir = File(rootProject.projectDir, "./core/core-util")
project(":core:core-ui").projectDir = File(rootProject.projectDir, "./core/core-ui")


// Include Flutter module wrapper (comment if flutter is embedded by aars)
val filePath: String = File(settingsDir, "flutter_module_wrapper/.android/include_flutter.groovy").absolutePath
if (File(filePath).exists()) {
    apply(from = filePath)
} else {
    throw GradleException("Không tìm thấy Flutter module tại: $filePath")
}
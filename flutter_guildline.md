# AAR (Only supports maven local)
- Step 1: cd `flutter_module_wrapper`
- Step 2: Run `build_aar.sh` to build the AAR and publish it to the local maven repo
- Step 3: In the `settings.gradle.kts` of the root project, comment out the following block:
```kotlin
    val filePath: String = File(settingsDir, "flutter_module_wrapper/.android/include_flutter.groovy").absolutePath
      if (File(filePath).exists()) {
          apply(from = filePath)
      } else {
          throw GradleException("Flutter module not found at: $filePath")
      }
```
- Step 4: In the `build.gradle.kts` of the module where you want to use the Flutter module, add the dependency (use the version and namespace from the AAR built in Step 2).
  Also, add `mavenLocal()` to the `repositories` block if it's not already there.
```kotlin
implementation("com.mdunggggg.flutter_module_wrapper:flutter_release:1.0")
```
- Step 5: Sync Gradle and run the project


# Module source code
- Step 1: In the `settings.gradle.kts` of the root project, uncomment the following block:
```kotlin
    val filePath: String = File(settingsDir, "flutter_module_wrapper/.android/include_flutter.groovy").absolutePath
      if (File(filePath).exists()) {
          apply(from = filePath)
      } else {
          throw GradleException("Flutter module not found at: $filePath")
      }
```
- Step 2: In the `gradle.properties` of the root project, configure `hostAppProjectName` (set it to the name of the module that will use the Flutter module).
```properties
hostAppProjectName=:apps:your_module_name
```
- Step 3: In the `build.gradle.kts` of the module where you want to use the Flutter module, add the dependency:
```kotlin
implementation(project(":flutter_module_wrapper"))
```
- Step 4: Sync Gradle and run the project

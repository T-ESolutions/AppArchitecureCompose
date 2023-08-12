rootProject.name = "AppArchitecture"

// Include all the existent modules in the project
rootDir
  .walk()
  .maxDepth(1)
  .filter {
    it.name != rootProject.name &&
      (
        it.name != "buildSrc" && it.isDirectory && file("${it.absolutePath}/build.gradle.kts").exists() ||
          it.name != "buildSrc" && it.isDirectory && file("${it.absolutePath}/build.gradle").exists()
        )
  }
  .forEach {
    include(":${it.name}")
  }
include(":network")
include(":core")
include(":features:auth")
include(":features:pink_ride")
include(":features:economy_ride")
include(":features:rent_ride")
include(":features:driver_settings")
include(":features:notifications")
include(":features:settings")

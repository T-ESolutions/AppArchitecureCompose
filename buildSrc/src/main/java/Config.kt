object Config {
    object AppConfig {
        const val appId = "app.te.hero_cars"
        const val compileSdkVersion = 34
        const val minSdkVersion = 23
        const val versionCode = 8
        const val versionCodeTest = 16
        const val versionName = "15-09-2022 (1.7)"
        const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
        const val cmakePath = "jni/CMakeLists.txt"
    }

    object Dependencies {
        const val snapshots = "https://oss.sonatype.org/content/repositories/snapshots/"
        const val jitPackURL = "https://jitpack.io"
        const val huwawiPackUrl = "https://developer.huawei.com/repo/"
        const val gradleVersion = "com.android.tools.build:gradle:${Versions.gradleVersion}"
        const val appGalleryConnect = "com.huawei.agconnect:agcp:${Versions.appGalleryConnect}"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val navigationSafeArgs =
            "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.androidNavigation}"
        const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltVersion}"
        const val google_services = "com.google.gms:google-services:${Versions.google_services}"
        const val google_secret =
            "com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:${Versions.google_secret}"
        const val proto_buf =
            "com.google.protobuf:protobuf-gradle-plugin:${Versions.classPath_protobuf}"

    }

    object Plugins {
        const val androidApplication = "com.android.application"
        const val androidLibrary = "com.android.library"
        const val kotlinAndroid = "org.jetbrains.kotlin.android"
        const val kotlinKapt = "kotlin-kapt"
        const val navigationSafeArgs = "androidx.navigation.safeargs"
        const val hilt = "dagger.hilt.android.plugin"
        const val kotlin_parcable = "kotlin-parcelize"
        const val ktLint = "org.jlleitschuh.gradle.ktlint"
        const val google_services = "com.google.gms.google-services"
        const val huawei_services = "com.huawei.agconnect"
        const val proto_buf = "com.google.protobuf"
        const val google_secrets = "com.google.android.libraries.mapsplatform.secrets-gradle-plugin"
        const val appsweep = "com.guardsquare.appsweep"
        const val checkDependencyUpdates = "name.remal.check-dependency-updates"
        const val kotlinCompilerExtensionVersion = "1.5.1"
    }

    object Modules {
        const val storage = ":storage"
        const val network = ":network"
        const val core = ":core"
        const val auth = ":features:auth"
        const val pink_ride = ":features:pink_ride"
        const val economy_ride = ":features:economy_ride"
        const val rent_ride = ":features:rent_ride"
        const val driver_settings = ":features:driver_settings"
        const val notifications = ":features:notifications"
        const val settings = ":features:settings"
        const val general = ":libs:general"
        const val navigation = ":Navigation"
    }

}
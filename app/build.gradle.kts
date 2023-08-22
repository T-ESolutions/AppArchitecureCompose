import com.google.protobuf.gradle.*

plugins {
    id(Config.Plugins.androidApplication)
    id(Config.Plugins.kotlinAndroid)
    id(Config.Plugins.kotlinKapt)
    id(Config.Plugins.kotlin_parcable)
//    id(Config.Plugins.navigationSafeArgs)
    id(Config.Plugins.hilt)
    id(Config.Plugins.google_secrets)
//    id(Config.Plugins.google_services)
//    id(Config.Plugins.huawei_services)
    id(Config.Plugins.checkDependencyUpdates) version "1.5.0"

//    id(Config.Plugins.appsweep) version "latest.release"
}

android {
    namespace = "app.te.hero_cars"
    compileSdk = Config.AppConfig.compileSdkVersion

    defaultConfig {
        applicationId = Config.AppConfig.appId
        minSdk = Config.AppConfig.minSdkVersion
        targetSdk = Config.AppConfig.compileSdkVersion
        versionCode = Config.AppConfig.versionCodeTest
        versionName = Config.AppConfig.versionName
        vectorDrawables.useSupportLibrary = true
        multiDexEnabled = true
        testInstrumentationRunner = Config.AppConfig.testRunner
        resourceConfigurations.apply {
            add("ar")
            add("en")
        }
    }

    buildTypes {
        signingConfigs {
            create("releaseConfig") {
                storeFile = file("/home/t-e-s/Osman.jks")
                storePassword = "te2018"
                keyAlias = "te"
                keyPassword = "te2018"
            }
//            create("debugConfig") {
//                storeFile = file("/home/t-e-s/Osman.jks")
//                storePassword = "te2018"
//                keyAlias = "te"
//                keyPassword = "te2018"
//            }

        }
        getByName("debug") {
//            signingConfig = signingConfigs.getByName("debugConfig")

            isDebuggable = true
            manifestPlaceholders["appName"] = "@string/app_name_debug"
            manifestPlaceholders["appIcon"] = "@mipmap/ic_launcher"
            manifestPlaceholders["appRoundIcon"] = "@mipmap/ic_launcher_round"
            applicationIdSuffix = ".debug"
        }



        getByName("release") {
            signingConfig = signingConfigs.getByName("releaseConfig")
            isMinifyEnabled = true
            isShrinkResources = true
            manifestPlaceholders["appName"] = "@string/app_name"
            manifestPlaceholders["appIcon"] = "@mipmap/ic_launcher"
            manifestPlaceholders["appRoundIcon"] = "@mipmap/ic_launcher_round"
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }

    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Config.Plugins.kotlinCompilerExtensionVersion

    }
    kotlinOptions {
        jvmTarget = "17"
    }

    lint {
//        isCheckReleaseBuilds = false
        // Or, if you prefer, you can continue to check for errors in release builds,
        // but continue the build even when errors are found:
//        isAbortOnError = false
    }
    buildFeatures {
        compose = true
        buildConfig =true
    }
//    appsweep {
//        apiKey = Libraries.appsweep_key
//    }
//    externalNativeBuild {
//        cmake {
//            path = file("CMakeLists.txt")
//        }
//    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    bundle {
        language {
            enableSplit = false
        }
    }
    kapt {
        correctErrorTypes = true
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))


    //Room
    implementation(Libraries.roomVersion)
    kapt(Libraries.roomCompiler)
    implementation(Libraries.roomktx)
    implementation(Libraries.roomCommon)
    implementation(Libraries.startup)

    // Networking
    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitConverter)
    implementation(Libraries.gson)
    implementation(Libraries.interceptor)
    implementation(Libraries.chuckLogging)


// paging
    implementation(Libraries.paging_compose)
    implementation(Libraries.paging_version)
    implementation(Libraries.paging_version_ktx)

    // Hilt
    implementation(Libraries.hilt)
    kapt(Libraries.hiltDaggerCompiler)
    kapt(Libraries.hilt_compose_compiler)

    // Firebase
    implementation(platform(Libraries.firebase_platform))
    implementation(Libraries.firebase_messaging)
    implementation(Libraries.hms_core)
    implementation(Libraries.hms_push)
    implementation(Libraries.hms_remote_config)


    // Arch Components
    implementation(Libraries.viewModel)
    implementation(Libraries.lifeData)
    implementation(Libraries.lifecycle)
    implementation(Libraries.viewModelState)

    // Kotlin Coroutines
    implementation(Libraries.coroutinesCore)
    implementation(Libraries.coroutinesAndroid)
    //DATA STORE
    implementation(Libraries.datastore_preferences)
    implementation(Libraries.datastore_core)
    implementation(Libraries.datastore_protobuf)



    implementation(Libraries.splash_screen)
    //Pin code
    implementation(Libraries.pin_code)
    //AdvancedWebView
    implementation(Libraries.AdvancedWebView)
    implementation(project(path =Config.Modules.core))
    implementation(project(path =Config.Modules.auth))
    implementation(project(path =Config.Modules.settings))

}

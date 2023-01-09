import com.google.protobuf.gradle.*

plugins {
    id(Config.Plugins.androidApplication)
    id(Config.Plugins.kotlinAndroid)
    id(Config.Plugins.kotlinKapt)
    id(Config.Plugins.kotlin_extensions)
    id(Config.Plugins.navigationSafeArgs)
    id(Config.Plugins.hilt)
    id(Config.Plugins.proto_buf)
    id(Config.Plugins.google_secrets)
//    id(Config.Plugins.google_services)
//    id(Config.Plugins.huawei_services)
    id(Config.Plugins.checkDependencyUpdates) version "1.5.0"
    id("org.jetbrains.kotlin.android")

//    id(Config.Plugins.appsweep) version "latest.release"
}
protobuf {
    protoc {
        artifact = Libraries.datastore_protoc
    }

    // Generates the java Protobuf-lite code for the Protobufs in this project. See
    // https://github.com/google/protobuf-gradle-plugin#customizing-protobuf-compilation
    // for more information.
    generateProtoTasks {
        all().forEach { task ->
            task.plugins {
                create("java") {
                    option("lite")
                }
            }
        }
    }
}

android {
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
            manifestPlaceholders["appName"] = "@string/app_name"
            manifestPlaceholders["appIcon"] = "@mipmap/ic_launcher"
            manifestPlaceholders["appRoundIcon"] = "@mipmap/ic_launcher_round"

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    dataBinding {
        isEnabled = true
    }
    lint {
        isCheckReleaseBuilds = false
        // Or, if you prefer, you can continue to check for errors in release builds,
        // but continue the build even when errors are found:
        isAbortOnError = false
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
//    appsweep {
//        apiKey = Libraries.appsweep_key
//    }
    externalNativeBuild {
        cmake {
            path = file("CMakeLists.txt")
        }
    }
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
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    //========== Compose================
    implementation(Libraries.compose_material_icon)
    implementation(Libraries.compose_run_time)
    implementation(Libraries.compose_ui)
    implementation(Libraries.compose_ui_tooling)
    implementation(Libraries.compose_ui_preview)
    implementation(Libraries.compose_foundation)
    implementation(Libraries.compose_material)
    implementation(Libraries.compose_material_window_size)
    implementation(Libraries.compose_activity)
    implementation(Libraries.compose_constraint_layout)
    implementation(Libraries.compose_pager)
    //Room
    implementation(Libraries.roomVersion)
    kapt(Libraries.roomCompiler)
    implementation(Libraries.roomktx)
    implementation(Libraries.roomCommon)
    implementation(Libraries.startup)
    implementation(Libraries.store_update)

    // Networking
    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitConverter)
    implementation(Libraries.gson)
    implementation(Libraries.interceptor)
    implementation(Libraries.chuckLogging)

    // Utils
    implementation(Libraries.playServices)
    implementation(Libraries.localization)
    implementation(Libraries.multidex)
    implementation(Libraries.permissions)
    implementation(Libraries.gson)
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


    // Support
    implementation(Libraries.appCompat)
    implementation(Libraries.coreKtx)
    implementation(Libraries.androidSupport)

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

    // =========Nav compose============
    implementation(Libraries.navigationHiltCompose)
    implementation(Libraries.navigationCompose)
    implementation(Libraries.navigationComposeAnimation)

    // UI
    implementation(Libraries.materialDesign)
    implementation(Libraries.navigationFragment)
    implementation(Libraries.navigationUI)
    implementation(Libraries.loadingAnimations)
    implementation(Libraries.alerter)
    implementation(Libraries.coil)
    implementation(Libraries.ssp)
    implementation(Libraries.ssdp)
    implementation(Libraries.shimmer)
    implementation(Libraries.splash_screen)
    //EXO PLAYER
    implementation(Libraries.exoplayer)
    implementation(Libraries.exoplayer_core)
    implementation(Libraries.exoplayer_dash)
    implementation(Libraries.exoplayer_ui)
    implementation(Libraries.exoplayer_smooth_streaming)
    //Pin code
    implementation(Libraries.pin_code)
    //AdvancedWebView
    implementation(Libraries.AdvancedWebView)

    // Project Modules
    implementation(project(Config.Modules.prettyPopUp))

}

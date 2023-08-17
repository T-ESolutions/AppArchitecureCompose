plugins {
    id(Config.Plugins.androidLibrary)
    id(Config.Plugins.kotlinAndroid)
    id(Config.Plugins.kotlinKapt)

}

android {
    namespace = "app.te.navigation"
    compileSdk = Config.AppConfig.compileSdkVersion

    defaultConfig {
        minSdk = Config.AppConfig.minSdkVersion

        testInstrumentationRunner = Config.AppConfig.testRunner
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    // =========Nav compose============
    implementation(Libraries.navigationHiltCompose)
    implementation(Libraries.navigationCompose)

}
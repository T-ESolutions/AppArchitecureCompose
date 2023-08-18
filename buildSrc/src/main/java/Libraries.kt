object Libraries {


    // Kotlin
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"

    // Arch Components
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifeData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val viewModelState =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}"
    const val paging_compose = "androidx.paging:paging-compose:${Versions.paging_compose}"
    const val paging_version = "androidx.paging:paging-runtime:${Versions.paging_version}"
    const val paging_version_ktx = "androidx.paging:paging-common-ktx:${Versions.paging_version}"
    const val roomVersion = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    const val roomktx = "androidx.room:room-ktx:${Versions.roomVersion}"
    const val roomCommon = "androidx.room:room-common:${Versions.roomVersion}"
    const val startup = "androidx.startup:startup-runtime:${Versions.startup}"
    const val store_update = "com.google.android.play:core:${Versions.store_update}"

    // Kotlin Coroutines
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}"

    // DATA STORE
    const val datastore_preferences =
        "androidx.datastore:datastore-preferences:${Versions.datastore_preferences}"
    const val datastore_core = "androidx.datastore:datastore-core:${Versions.datastore_core}"
    const val datastore_protobuf =
        "com.google.protobuf:protobuf-javalite:${Versions.datastore_protobuf}"
    const val datastore_protoc =
        "com.google.protobuf:protoc:${Versions.datastore_protoc}"

    // Networking
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
    const val chuckLogging = "com.github.chuckerteam.chucker:library:${Versions.chuckLogging}"

    // UI
    const val navigationHiltCompose =
        "androidx.hilt:hilt-navigation-compose:${Versions.navigationHiltCompose}"
    const val navigationCompose =
        "androidx.navigation:navigation-compose:${Versions.androidNavigation}"
    const val loadingAnimations = "com.airbnb.android:lottie-compose:${Versions.loadingAnimations}"
    const val alerter = "com.github.tapadoo:alerter:${Versions.alerter}"
    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"
    const val ssp = "com.intuit.ssp:ssp-android:${Versions.ssp}"
    const val ssdp = "com.intuit.sdp:sdp-android:${Versions.ssp}"
    const val shimmer = "com.facebook.shimmer:shimmer:${Versions.shimmer}"
    const val splash_screen = "androidx.core:core-splashscreen:${Versions.splash_screen}"

    // EXO PLAYER
    const val exoplayer = "com.google.android.exoplayer:exoplayer:${Versions.exoplayer}"
    const val exoplayer_core = "com.google.android.exoplayer:exoplayer-core:${Versions.exoplayer}"
    const val exoplayer_dash = "com.google.android.exoplayer:exoplayer-dash:${Versions.exoplayer}"
    const val exoplayer_ui = "com.google.android.exoplayer:exoplayer-ui:${Versions.exoplayer}"
    const val exoplayer_smooth_streaming =
        "com.google.android.exoplayer:exoplayer-smoothstreaming:${Versions.exoplayer}"

    // Utils
    const val playServices = "com.google.android.gms:play-services-auth:${Versions.playServices}"
    const val localization =
        "com.zeugmasolutions.localehelper:locale-helper-android:${Versions.localization}"
    const val multidex = "androidx.multidex:multidex:${Versions.multidex}"

    // Hilt
    const val hilt = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
    const val hiltDaggerCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"

    // Hilt compose
    const val hilt_compose_compiler =
        "androidx.hilt:hilt-compiler:${Versions.hilt_compose_compiler}"

    // Map
    const val compose_material_icon = "androidx.compose.material:material-icons-extended:${Versions.compose_material_icon}"
    const val compose_run_time = "androidx.compose.runtime:runtime-livedata:${Versions.compose_ui}"
    const val compose_ui = "androidx.compose.ui:ui:${Versions.compose_ui}"
    const val compose_ui_tooling =
        "androidx.compose.ui:ui-tooling:${Versions.compose_ui}"
    const val compose_ui_preview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose_ui}"
    const val compose_foundation = "androidx.compose.foundation:foundation:${Versions.compose_foundation}"
    const val compose_material = "androidx.compose.material3:material3:${Versions.compose_material}"
    const val compose_material_window_size = "androidx.compose.material3:material3-window-size-class:${Versions.compose_material}"
    const val compose_activity = "androidx.activity:activity-compose:${Versions.compose_activity}"
    const val compose_constraint_layout = "androidx.constraintlayout:constraintlayout-compose:${Versions.compose_constraint_layout}"
    const val compose_pager = "com.google.accompanist:accompanist-pager:${Versions.compose_pager}"
    const val compose_lifeCycle = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.compose_pager}"

    //FireBase
    const val firebase_platform = "com.google.firebase:firebase-bom:${Versions.firebase}"
    const val firebase_messaging = "com.google.firebase:firebase-messaging-ktx"
    const val hms_push = "com.huawei.hms:push:${Versions.hms_push}"
    const val hms_core = "com.huawei.agconnect:agconnect-core:${Versions.hms_core}"
    const val hms_remote_config =
        "com.huawei.agconnect:agconnect-remoteconfig:${Versions.hms_remote_config}"

    //pin code
    const val pin_code = "io.github.chaosleung:pinview:${Versions.pin_code}"

    const val AdvancedWebView =
        "com.github.delight-im:Android-AdvancedWebView:${Versions.AdvancedWebView}"

    const val appsweep_key = "gs_appsweep_IDv9AgD_JEkqGn8O46tmbwExhDdTZLCJlBYj1kaq"

}
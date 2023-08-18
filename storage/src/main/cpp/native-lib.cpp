//
// Created by t-e-s on ٢٠‏/٦‏/٢٠٢٢.
//

#include <string>
#include <jni.h>

extern "C" jstring
Java_te_app_storage_Keys_debugBaseUrl(JNIEnv *env, jobject thiz) {
    std::string debugBaseUrl = "https://stolen-phones.com/api/";
    return env->NewStringUTF(debugBaseUrl.c_str());
}

extern "C" jstring
Java_te_app_storage_Keys_releaseBaseUrl(JNIEnv *env, jobject thiz) {
    std::string releaseBaseUrl = "https://stolen-phones.com/api/";
    return env->NewStringUTF(releaseBaseUrl.c_str());
}
extern "C" jstring
Java_te_app_storage_Keys_appDataStore(JNIEnv *env, jobject thiz) {
    std::string app_data_store = "app_data_store";
    return env->NewStringUTF(app_data_store.c_str());
}

extern "C" jstring
Java_te_app_storage_Keys_appDataStoreFirstTime(JNIEnv *env, jobject thiz) {
    std::string app_data_store_first_time = "app_data_store_first_time";
    return env->NewStringUTF(app_data_store_first_time.c_str());
}
extern "C" jstring
Java_te_app_storage_Keys_userDataStoreFileName(JNIEnv *env, jobject thiz) {
    std::string user_store = "user_store.pb";
    return env->NewStringUTF(user_store.c_str());
}
extern "C" jstring
Java_te_app_storage_Keys_firebaseToken(JNIEnv *env, jobject thiz) {
    std::string firebaseToken = "FIREBASE_TOKEN.pb";
    return env->NewStringUTF(firebaseToken.c_str());
}
extern "C" jstring
Java_te_app_storage_Keys_userToken(JNIEnv *env, jobject thiz) {
    std::string userToken = "USER_TOKEN";
    return env->NewStringUTF(userToken.c_str());
}

extern "C" jstring
Java_te_app_storage_Keys_firstTime(JNIEnv *env, jobject thiz) {
    std::string firstTime = "FIRST_TIME";
    return env->NewStringUTF(firstTime.c_str());
}
extern "C" jstring
Java_te_app_storage_Keys_isLoggedIn(JNIEnv *env, jobject thiz) {
    std::string isLoggedIn = "isLoggedIn";
    return env->NewStringUTF(isLoggedIn.c_str());
}
extern "C" jstring
Java_te_app_storage_Keys_lang(JNIEnv *env, jobject thiz) {
    std::string lang = "LANG";
    return env->NewStringUTF(lang.c_str());
}
extern "C" jstring
Java_te_app_storage_Keys_splash(JNIEnv *env, jobject thiz) {
    std::string splash = "splash";
    return env->NewStringUTF(splash.c_str());
}

extern "C" jstring
Java_te_app_storage_Keys_platForm(JNIEnv *env, jobject thiz) {
    std::string platform = "android";
    return env->NewStringUTF(platform.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_te_app_storage_Keys_userTypeKey(JNIEnv *env, jobject thiz) {
    std::string platform = "user_type";
    return env->NewStringUTF(platform.c_str());
}
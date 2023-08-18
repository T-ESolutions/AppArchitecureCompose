package te.app.storage

object Keys {
    init {
        System.loadLibrary("native-lib")
    }

    private external fun debugBaseUrl(): String
    private external fun releaseBaseUrl(): String
    external fun appDataStore(): String
    external fun appDataStoreFirstTime(): String
    external fun userDataStoreFileName(): String
    external fun firebaseToken(): String
    external fun userToken(): String
    external fun firstTime(): String
    external fun isLoggedIn(): String
    external fun lang(): String
    external fun splash(): String
    external fun platForm(): String
    external fun userTypeKey(): String

    fun baseUrl(): String {
        return if (BuildConfig.DEBUG)
            debugBaseUrl()
        else
            releaseBaseUrl()
    }
}
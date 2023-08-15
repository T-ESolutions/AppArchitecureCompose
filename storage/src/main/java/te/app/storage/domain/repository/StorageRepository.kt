package te.app.storage.domain.repository

import com.structure.base_mvvm.User
import kotlinx.coroutines.flow.Flow

interface StorageRepository {

    suspend fun isLoggedIn(isLoggedIn: Boolean)

    suspend fun getIsLoggedIn(): Flow<Boolean>

    suspend fun saveFirebaseTokenToLocal(firebaseToken: String)

    suspend fun getFirebaseTokenToLocal(): Flow<String>

    suspend fun setFirstTime(isFirstTime: Boolean)

    suspend fun isFirstTime(): Flow<Boolean>

    suspend fun saveUserToLocal(user: Any)

    suspend fun getUserToLocal(): Flow<User>

    suspend fun saveUserToken(userToken: String)

    suspend fun getUserToken(): Flow<String>

    suspend fun setLang(lang: String)

    suspend fun getLang(): Flow<String>

    suspend fun saveSplash(value: String)

    suspend fun getSplash(): Flow<String>

    suspend fun clearPreferences()

}
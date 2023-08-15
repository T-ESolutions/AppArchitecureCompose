package app.te.hero_cars.domain.account.repository

import app.te.hero_cars.domain.account.entity.request.SendFirebaseTokenRequest
import app.te.hero_cars.domain.auth.entity.model.UserResponse
import app.te.network.utils.BaseResponse
import app.te.network.utils.Resource
import com.structure.base_mvvm.User
import kotlinx.coroutines.flow.Flow

interface AccountRepository {

    suspend fun sendFirebaseToken(request: SendFirebaseTokenRequest): app.te.network.utils.Resource<app.te.network.utils.BaseResponse<*>>

    suspend fun logOut(): app.te.network.utils.Resource<app.te.network.utils.BaseResponse<*>>

    suspend fun isLoggedIn(isLoggedIn: Boolean)

    suspend fun getIsLoggedIn(): Flow<Boolean>

    suspend fun saveFirebaseTokenToLocal(firebaseToken: String)

    suspend fun getFirebaseTokenToLocal(): Flow<String>

    suspend fun setFirstTime(isFirstTime: Boolean)

    suspend fun isFirstTime(): Flow<Boolean>

    suspend fun saveUserToLocal(user: UserResponse)

    suspend fun getUserToLocal(): Flow<User>

    suspend fun saveUserToken(userToken: String)

    suspend fun getUserToken(): Flow<String>

    suspend fun setLang(lang: String)

    suspend fun getLang(): Flow<String>

    suspend fun saveSplash(value: String)

    suspend fun getSplash(): Flow<String>

    suspend fun clearPreferences()

}
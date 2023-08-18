package te.app.storage.data.repository

import com.structure.base_mvvm.User
import kotlinx.coroutines.flow.Flow
import te.app.storage.data_store.AppPreferences
import te.app.storage.domain.repository.StorageRepository
import javax.inject.Inject

class StorageRepositoryImpl @Inject constructor(
  private val appPreferences: AppPreferences
) : StorageRepository {


  override suspend fun isLoggedIn(isLoggedIn: Boolean) {
    appPreferences.isLoggedIn(isLoggedIn)
  }

  override suspend fun getIsLoggedIn(): Flow<Boolean> {
    return appPreferences.getIsLoggedIn()
  }


  override suspend fun saveFirebaseTokenToLocal(firebaseToken: String) {
    appPreferences.saveFireBaseToken(firebaseToken)
  }

  override suspend fun getFirebaseTokenToLocal(): Flow<String> {
    return appPreferences.getFireBaseToken()
  }

  override suspend fun setFirstTime(isFirstTime: Boolean) {
    appPreferences.isFirstTime(isFirstTime)
  }

  override suspend fun isFirstTime(): Flow<Boolean> {
    return appPreferences.getIsFirstTime()
  }

  override suspend fun saveUserToLocal(user: Any) {
    //TODO UN COMMENT
//    appPreferences.saveUser(user)
  }

  override suspend fun getUserToLocal(): User {
    return appPreferences.getUserValue()
  }

  override suspend fun saveUserToken(userToken: String) {
    appPreferences.userToken(userToken)
  }

  override suspend fun getUserToken(): Flow<String> {
    return appPreferences.getUserToken()
  }
  override suspend fun saveUserType(userType: Int) {
    appPreferences.saveUserType(userType)
  }

  override suspend fun getUserType(): Int {
    return appPreferences.getUserType()
  }

  override suspend fun setLang(lang: String) {
    appPreferences.setLang(lang)
  }

  override suspend fun getLang(): Flow<String> {
    return appPreferences.getLang()
  }

  override suspend fun saveSplash(value: String) {
    appPreferences.saveSplash(value)
  }

  override suspend fun getSplash(): Flow<String> {
    return appPreferences.getSplash()
  }

  override
  suspend fun clearPreferences() = appPreferences.clearPreferences()
}
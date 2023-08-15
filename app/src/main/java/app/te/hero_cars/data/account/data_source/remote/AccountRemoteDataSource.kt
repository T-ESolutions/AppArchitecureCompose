package app.te.hero_cars.data.account.data_source.remote

import app.te.hero_cars.domain.account.entity.request.SendFirebaseTokenRequest
import app.te.network.BaseRemoteDataSource
import javax.inject.Inject

class AccountRemoteDataSource @Inject constructor(private val apiService: AccountServices) :
  BaseRemoteDataSource() {

  suspend fun sendFirebaseToken(request: SendFirebaseTokenRequest) = safeApiCall {
    apiService.sendFirebaseToken(request)
  }

  suspend fun logOut() = safeApiCall {
    apiService.logOut()
  }
}
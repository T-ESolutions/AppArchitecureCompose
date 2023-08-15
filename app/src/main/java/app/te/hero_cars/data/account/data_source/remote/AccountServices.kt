package app.te.hero_cars.data.account.data_source.remote

import app.te.hero_cars.domain.account.entity.request.SendFirebaseTokenRequest
import app.te.network.utils.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface AccountServices {

  @PUT("v1/firebase_token")
  suspend fun sendFirebaseToken(@Body request: SendFirebaseTokenRequest): app.te.network.utils.BaseResponse<*>

  @POST("v1/user/logout")
  suspend fun logOut(): app.te.network.utils.BaseResponse<*>
}
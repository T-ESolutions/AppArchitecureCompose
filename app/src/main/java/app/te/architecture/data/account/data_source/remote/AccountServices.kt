package app.te.architecture.data.account.data_source.remote

import app.te.architecture.domain.account.entity.request.SendFirebaseTokenRequest
import app.te.architecture.domain.utils.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface AccountServices {

  @PUT("v1/firebase_token")
  suspend fun sendFirebaseToken(@Body request: SendFirebaseTokenRequest): BaseResponse<*>

  @POST("v1/user/logout")
  suspend fun logOut(): BaseResponse<*>
}
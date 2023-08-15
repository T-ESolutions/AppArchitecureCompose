package te.app.auth.data.data_source.remote

import app.te.network.utils.BaseResponse
import te.app.auth.domain.entity.model.UserResponse
import retrofit2.http.*
import te.app.auth.domain.entity.model.AppTutorialModel
import te.app.auth.domain.entity.request.ForgetPasswordRequest
import te.app.auth.domain.entity.request.LogInRequest
import te.app.auth.domain.entity.request.RegisterRequest
import te.app.auth.domain.entity.request.UpdatePassword

interface AuthServices {

    @POST("V1/auth/log-in")
    suspend fun logIn(@Body request: LogInRequest): BaseResponse<UserResponse>

    @POST("v1/auth/forget-password")
    suspend fun forgetPassword(@Body request: ForgetPasswordRequest): BaseResponse<*>

    @POST("v1/auth/resend-code")
    suspend fun resendCode(@Body request: ForgetPasswordRequest): BaseResponse<*>

    @POST("v1/auth/verify_phone")
    suspend fun verifyAccount(@Body request: RegisterRequest): BaseResponse<UserResponse>

    @POST("v1/auth/verify")
    suspend fun verifyPasswordAccount(@Body request: ForgetPasswordRequest): BaseResponse<*>

    @POST("v1/user/profile/update_password")
    suspend fun changePassword(@Body request: UpdatePassword): BaseResponse<*>

    @POST("v1/auth/change-password")
    suspend fun authChangePassword(@Body request: UpdatePassword): BaseResponse<*>

    @POST("V1/auth/sign-up")
    suspend fun register(@Body request: RegisterRequest): BaseResponse<UserResponse>

    @GET("V1/app/screens")
    suspend fun intro(): BaseResponse<List<AppTutorialModel>>

}
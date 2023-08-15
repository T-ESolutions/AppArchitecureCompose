package app.te.hero_cars.data.auth.data_source.remote

import app.te.hero_cars.domain.auth.entity.model.UserResponse
import app.te.hero_cars.domain.auth.entity.request.*
import app.te.hero_cars.domain.profile.entity.UpdatePassword
import app.te.network.utils.BaseResponse
import retrofit2.http.*

interface AuthServices {

    @POST("V1/auth/log-in")
    suspend fun logIn(@Body request: LogInRequest): app.te.network.utils.BaseResponse<UserResponse>

    @POST("v1/auth/forget-password")
    suspend fun forgetPassword(@Body request: ForgetPasswordRequest): app.te.network.utils.BaseResponse<*>

    @POST("v1/auth/resend-code")
    suspend fun resendCode(@Body request: ForgetPasswordRequest): app.te.network.utils.BaseResponse<*>

    @POST("v1/auth/verify_phone")
    suspend fun verifyAccount(@Body request: RegisterRequest): app.te.network.utils.BaseResponse<UserResponse>

    @POST("v1/auth/verify")
    suspend fun verifyPasswordAccount(@Body request: ForgetPasswordRequest): app.te.network.utils.BaseResponse<*>

    @POST("v1/user/profile/update_password")
    suspend fun changePassword(@Body request: UpdatePassword): app.te.network.utils.BaseResponse<*>

    @POST("v1/auth/change-password")
    suspend fun authChangePassword(@Body request: UpdatePassword): app.te.network.utils.BaseResponse<*>

    @POST("V1/auth/sign-up")
    suspend fun register(@Body request: RegisterRequest): app.te.network.utils.BaseResponse<UserResponse>

}
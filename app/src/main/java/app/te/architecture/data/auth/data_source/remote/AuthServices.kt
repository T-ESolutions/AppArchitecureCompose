package app.te.architecture.data.auth.data_source.remote

import app.te.architecture.domain.auth.entity.model.UserResponse
import app.te.architecture.domain.auth.entity.request.*
import app.te.architecture.domain.profile.entity.UpdatePassword
import app.te.architecture.domain.utils.BaseResponse
import retrofit2.http.*

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

}
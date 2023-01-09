package app.te.architecture.domain.auth.repository

import app.te.architecture.domain.auth.entity.model.UserResponse
import app.te.architecture.domain.auth.entity.request.*
import app.te.architecture.domain.profile.entity.UpdatePassword
import app.te.architecture.domain.utils.BaseResponse
import app.te.architecture.domain.utils.Resource

interface AuthRepository {

    suspend fun logIn(request: LogInRequest): Resource<BaseResponse<UserResponse>>
    suspend fun signUp(request: RegisterRequest): Resource<BaseResponse<UserResponse>>
    suspend fun changePassword(request: UpdatePassword): Resource<BaseResponse<*>>
    suspend fun authChangePassword(request: UpdatePassword): Resource<BaseResponse<*>>
    suspend fun forgetPassword(request: ForgetPasswordRequest): Resource<BaseResponse<*>>
    suspend fun resendCode(request: ForgetPasswordRequest): Resource<BaseResponse<*>>
    suspend fun verifyAccount(request: RegisterRequest): Resource<BaseResponse<UserResponse>>
    suspend fun verifyPasswordAccount(request: ForgetPasswordRequest): Resource<BaseResponse<*>>
}
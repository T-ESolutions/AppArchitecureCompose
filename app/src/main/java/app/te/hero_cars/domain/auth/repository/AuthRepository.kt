package app.te.hero_cars.domain.auth.repository

import app.te.hero_cars.domain.auth.entity.model.UserResponse
import app.te.hero_cars.domain.auth.entity.request.*
import app.te.hero_cars.domain.profile.entity.UpdatePassword
import app.te.network.utils.BaseResponse
import app.te.network.utils.Resource

interface AuthRepository {

    suspend fun logIn(request: LogInRequest): app.te.network.utils.Resource<app.te.network.utils.BaseResponse<UserResponse>>
    suspend fun signUp(request: RegisterRequest): app.te.network.utils.Resource<app.te.network.utils.BaseResponse<UserResponse>>
    suspend fun changePassword(request: UpdatePassword): app.te.network.utils.Resource<app.te.network.utils.BaseResponse<*>>
    suspend fun authChangePassword(request: UpdatePassword): app.te.network.utils.Resource<app.te.network.utils.BaseResponse<*>>
    suspend fun forgetPassword(request: ForgetPasswordRequest): app.te.network.utils.Resource<app.te.network.utils.BaseResponse<*>>
    suspend fun resendCode(request: ForgetPasswordRequest): app.te.network.utils.Resource<app.te.network.utils.BaseResponse<*>>
    suspend fun verifyAccount(request: RegisterRequest): app.te.network.utils.Resource<app.te.network.utils.BaseResponse<UserResponse>>
    suspend fun verifyPasswordAccount(request: ForgetPasswordRequest): app.te.network.utils.Resource<app.te.network.utils.BaseResponse<*>>
}
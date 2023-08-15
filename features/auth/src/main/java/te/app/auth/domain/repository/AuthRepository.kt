package te.app.auth.domain.repository

import te.app.auth.domain.entity.model.UserResponse
import app.te.network.utils.BaseResponse
import app.te.network.utils.Resource
import te.app.auth.domain.entity.model.AppTutorialModel
import te.app.auth.domain.entity.request.ForgetPasswordRequest
import te.app.auth.domain.entity.request.LogInRequest
import te.app.auth.domain.entity.request.RegisterRequest
import te.app.auth.domain.entity.request.UpdatePassword

interface AuthRepository {

    suspend fun logIn(request: LogInRequest): Resource<BaseResponse<UserResponse>>
    suspend fun signUp(request: RegisterRequest): Resource<BaseResponse<UserResponse>>
    suspend fun changePassword(request: UpdatePassword): Resource<BaseResponse<*>>
    suspend fun authChangePassword(request: UpdatePassword): Resource<BaseResponse<*>>
    suspend fun forgetPassword(request: ForgetPasswordRequest): Resource<BaseResponse<*>>
    suspend fun resendCode(request: ForgetPasswordRequest): Resource<BaseResponse<*>>
    suspend fun verifyAccount(request: RegisterRequest): Resource<BaseResponse<UserResponse>>
    suspend fun verifyPasswordAccount(request: ForgetPasswordRequest): Resource<BaseResponse<*>>
    suspend fun intro(): Resource<BaseResponse<List<AppTutorialModel>>>

}
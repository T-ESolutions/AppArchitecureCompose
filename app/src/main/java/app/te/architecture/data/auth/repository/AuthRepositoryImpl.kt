package app.te.architecture.data.auth.repository

import app.te.architecture.data.auth.data_source.remote.AuthRemoteDataSource
import app.te.architecture.data.local.preferences.AppPreferences
import app.te.architecture.domain.auth.entity.model.UserResponse
import app.te.architecture.domain.auth.entity.request.*
import app.te.architecture.domain.auth.repository.AuthRepository
import app.te.architecture.domain.profile.entity.UpdatePassword
import app.te.architecture.domain.utils.BaseResponse
import app.te.architecture.domain.utils.Resource
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource,
    private val appPreferences: AppPreferences
) : AuthRepository {

    override
    suspend fun logIn(request: LogInRequest): Resource<BaseResponse<UserResponse>> {
        val response = remoteDataSource.logIn(request)
        if (response is Resource.Success) {
            appPreferences.userToken(response.value.data.accessToken)
            appPreferences.saveUser(response.value.data)
        }
        return response
    }

    override suspend fun signUp(request: RegisterRequest): Resource<BaseResponse<UserResponse>> {
        val response = remoteDataSource.register(request)
        if (response is Resource.Success) {
            appPreferences.userToken(response.value.data.accessToken)
            appPreferences.saveUser(response.value.data)
        }
        return response
    }

    override suspend fun changePassword(request: UpdatePassword): Resource<BaseResponse<*>> =
        remoteDataSource.changePassword(request)

    override suspend fun authChangePassword(request: UpdatePassword): Resource<BaseResponse<*>> =
        remoteDataSource.authChangePassword(request)

    override suspend fun forgetPassword(request: ForgetPasswordRequest) =
        remoteDataSource.forgetPassword(request)

    override suspend fun resendCode(request: ForgetPasswordRequest) =
        remoteDataSource.resendCode(request)


    override suspend fun verifyAccount(request: RegisterRequest): Resource<BaseResponse<UserResponse>> {
        val response = remoteDataSource.verifyAccount(request)
        if (response is Resource.Success) {
            appPreferences.userToken(response.value.data.accessToken)
            appPreferences.saveUser(response.value.data)
        }
        return response
    }


    override suspend fun verifyPasswordAccount(request: ForgetPasswordRequest): Resource<BaseResponse<*>> =
        remoteDataSource.verifyPasswordAccount(request)

}


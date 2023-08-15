package te.app.auth.data.data_source.remote

import te.app.auth.domain.entity.request.ForgetPasswordRequest
import te.app.auth.domain.entity.request.LogInRequest
import te.app.auth.domain.entity.request.RegisterRequest
import te.app.auth.domain.entity.request.UpdatePassword
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val apiService: AuthServices
) : app.te.network.BaseRemoteDataSource() {

    suspend fun logIn(request: LogInRequest) = safeApiCall {
        apiService.logIn(request)
    }


    suspend fun forgetPassword(request: ForgetPasswordRequest) = safeApiCall {
        apiService.forgetPassword(request)
    }

    suspend fun resendCode(request: ForgetPasswordRequest) = safeApiCall {
        apiService.resendCode(request)
    }

    suspend fun verifyAccount(request: RegisterRequest) = safeApiCall {
        apiService.verifyAccount(request)
    }

    suspend fun verifyPasswordAccount(request: ForgetPasswordRequest) = safeApiCall {
        apiService.verifyPasswordAccount(request)
    }

    suspend fun changePassword(request: UpdatePassword) = safeApiCall {
        apiService.changePassword(request)
    }

    suspend fun authChangePassword(request: UpdatePassword) = safeApiCall {
        apiService.authChangePassword(request)
    }

    suspend fun register(request: RegisterRequest) = safeApiCall {
        apiService.register(request)
    }
    suspend fun intro() = safeApiCall {
        apiService.intro()
    }
}
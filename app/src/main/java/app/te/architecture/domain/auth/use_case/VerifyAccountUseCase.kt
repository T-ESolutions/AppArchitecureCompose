package app.te.architecture.domain.auth.use_case

import app.te.architecture.domain.auth.entity.model.UserResponse
import app.te.architecture.domain.auth.entity.request.ForgetPasswordRequest
import app.te.architecture.domain.auth.entity.request.RegisterRequest
import app.te.architecture.domain.auth.repository.AuthRepository
import app.te.architecture.domain.utils.BaseResponse
import app.te.architecture.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class VerifyAccountUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    operator fun invoke(request: RegisterRequest): Flow<Resource<BaseResponse<UserResponse>>> =
        flow {
//            if (request.otp.isNotEmpty()) {
                emit(Resource.Loading)
                emit(authRepository.verifyAccount(request))
//            }
        }.flowOn(Dispatchers.IO)

    operator fun invoke(request: ForgetPasswordRequest): Flow<Resource<BaseResponse<*>>> =
        flow {
            if (request.code.isNotEmpty()) {
                emit(Resource.Loading)
                emit(authRepository.verifyPasswordAccount(request))
            }
        }.flowOn(Dispatchers.IO)

}
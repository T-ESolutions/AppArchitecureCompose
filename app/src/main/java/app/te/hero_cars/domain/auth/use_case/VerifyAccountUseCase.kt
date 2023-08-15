package app.te.hero_cars.domain.auth.use_case

import app.te.hero_cars.domain.auth.entity.model.UserResponse
import app.te.hero_cars.domain.auth.entity.request.ForgetPasswordRequest
import app.te.hero_cars.domain.auth.entity.request.RegisterRequest
import app.te.hero_cars.domain.auth.repository.AuthRepository
import app.te.network.utils.BaseResponse
import app.te.network.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class VerifyAccountUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    operator fun invoke(request: RegisterRequest): Flow<app.te.network.utils.Resource<app.te.network.utils.BaseResponse<UserResponse>>> =
        flow {
//            if (request.otp.isNotEmpty()) {
                emit(app.te.network.utils.Resource.Loading)
                emit(authRepository.verifyAccount(request))
//            }
        }.flowOn(Dispatchers.IO)

    operator fun invoke(request: ForgetPasswordRequest): Flow<app.te.network.utils.Resource<app.te.network.utils.BaseResponse<*>>> =
        flow {
            if (request.code.isNotEmpty()) {
                emit(app.te.network.utils.Resource.Loading)
                emit(authRepository.verifyPasswordAccount(request))
            }
        }.flowOn(Dispatchers.IO)

}
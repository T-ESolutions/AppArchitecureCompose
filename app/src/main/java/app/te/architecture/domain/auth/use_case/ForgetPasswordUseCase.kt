package app.te.architecture.domain.auth.use_case

import app.te.architecture.domain.auth.entity.request.ForgetPasswordRequest
import app.te.architecture.domain.auth.repository.AuthRepository
import app.te.architecture.domain.utils.BaseResponse
import app.te.architecture.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class ForgetPasswordUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(request: ForgetPasswordRequest): Flow<Resource<BaseResponse<*>>> = flow {
        if (request.phone.isNotEmpty()) {
            emit(Resource.Loading)
            emit(authRepository.forgetPassword(request))
        }
    }.flowOn(Dispatchers.IO)
}
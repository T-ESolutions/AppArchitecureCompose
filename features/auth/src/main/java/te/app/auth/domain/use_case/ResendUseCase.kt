package te.app.auth.domain.use_case

import te.app.auth.domain.entity.request.ForgetPasswordRequest
import te.app.auth.domain.repository.AuthRepository
import app.te.network.utils.BaseResponse
import app.te.network.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class ResendUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(request: ForgetPasswordRequest): Flow<Resource<BaseResponse<*>>> = flow {
        if (request.phone.isNotEmpty()) {
            emit(Resource.Loading)
            emit(authRepository.resendCode(request))
        }
    }.flowOn(Dispatchers.IO)
}
package te.app.auth.domain.use_case

import te.app.auth.domain.entity.model.UserResponse
import te.app.auth.domain.entity.request.LogInRequest
import te.app.auth.domain.repository.AuthRepository
import app.te.network.utils.BaseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class LogInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(
        request: LogInRequest
    ): Flow<app.te.network.utils.Resource<BaseResponse<UserResponse>>> = flow {
        emit(app.te.network.utils.Resource.Loading)
        val result = authRepository.logIn(request)
        emit(result)
    }.flowOn(Dispatchers.IO)

}
package app.te.architecture.domain.auth.use_case

import app.te.architecture.domain.auth.entity.model.UserResponse
import app.te.architecture.domain.auth.entity.request.LogInRequest
import app.te.architecture.domain.auth.repository.AuthRepository
import app.te.architecture.domain.utils.*
import app.te.architecture.presentation.base.utils.Constants
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
    ): Flow<Resource<BaseResponse<UserResponse>>> = flow {
        emit(Resource.Loading)
        val result = authRepository.logIn(request)
        emit(result)
    }.flowOn(Dispatchers.IO)

}
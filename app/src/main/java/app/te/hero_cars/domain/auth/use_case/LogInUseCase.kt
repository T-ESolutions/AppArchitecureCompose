package app.te.hero_cars.domain.auth.use_case

import app.te.hero_cars.domain.auth.entity.model.UserResponse
import app.te.hero_cars.domain.auth.entity.request.LogInRequest
import app.te.hero_cars.domain.auth.repository.AuthRepository
import app.te.hero_cars.domain.utils.*
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
    ): Flow<app.te.network.utils.Resource<app.te.network.utils.BaseResponse<UserResponse>>> = flow {
        emit(app.te.network.utils.Resource.Loading)
        val result = authRepository.logIn(request)
        emit(result)
    }.flowOn(Dispatchers.IO)

}
package app.te.architecture.domain.auth.use_case

import app.te.architecture.domain.auth.entity.model.UserResponse
import app.te.architecture.domain.auth.entity.request.RegisterRequest
import app.te.architecture.domain.auth.repository.AuthRepository
import app.te.architecture.domain.utils.BaseResponse
import app.te.architecture.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class RegisterUseCase @Inject constructor(
  private val authRepository: AuthRepository
) {

  operator fun invoke(request: RegisterRequest): Flow<Resource<BaseResponse<UserResponse>>> = flow {
      emit(Resource.Loading)
      emit(authRepository.signUp(request))
  }.flowOn(Dispatchers.IO)


}
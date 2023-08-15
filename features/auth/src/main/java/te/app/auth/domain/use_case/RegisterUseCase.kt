package te.app.auth.domain.use_case

import te.app.auth.domain.entity.model.UserResponse
import te.app.auth.domain.entity.request.RegisterRequest
import te.app.auth.domain.repository.AuthRepository
import app.te.network.utils.BaseResponse
import app.te.network.utils.Resource
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
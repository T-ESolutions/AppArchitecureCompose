package te.app.auth.domain.use_case

import te.app.auth.domain.repository.AuthRepository
import javax.inject.Inject


class SocialLogInUseCase @Inject constructor(
    private val authRepository: AuthRepository,
//    private val userLocalUseCase: UserLocalUseCase
) {
//  operator fun invoke(
//    request: SocialLogInRequest
//  ): Flow<Resource<BaseResponse<UserResponse>>> = flow {
//    emit(Resource.Loading)
//    val result = authRepository.getSubscriptionsTypes(request)
//    if (result is Resource.Success) {
//      userLocalUseCase.saveUserToken(result.value.data.jwt)
//      userLocalUseCase.invoke(result.value.data)
//    }
//    emit(result)
//  }.flowOn(Dispatchers.IO)

}
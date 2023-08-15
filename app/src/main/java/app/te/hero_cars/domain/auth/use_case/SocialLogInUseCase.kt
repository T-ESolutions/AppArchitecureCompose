package app.te.hero_cars.domain.auth.use_case

import app.te.hero_cars.domain.account.use_case.UserLocalUseCase
import app.te.hero_cars.domain.auth.repository.AuthRepository
import javax.inject.Inject


class SocialLogInUseCase @Inject constructor(
  private val authRepository: AuthRepository,
  private val userLocalUseCase: UserLocalUseCase
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
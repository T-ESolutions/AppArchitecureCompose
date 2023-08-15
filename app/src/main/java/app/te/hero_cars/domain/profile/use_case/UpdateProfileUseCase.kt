package app.te.hero_cars.domain.profile.use_case

import te.app.auth.domain.entity.model.UserResponse
import app.te.hero_cars.domain.profile.entity.UpdateProfileRequest
import app.te.hero_cars.domain.profile.repository.ProfileRepository
import app.te.network.utils.BaseResponse
import app.te.network.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class UpdateProfileUseCase @Inject constructor(
  private val profileRepository: ProfileRepository
) {

  operator fun invoke(
    request: UpdateProfileRequest
  ): Flow<app.te.network.utils.Resource<app.te.network.utils.BaseResponse<UserResponse>>> = flow {
    if (checkValidation(request)) {
      emit(app.te.network.utils.Resource.Loading)
      emit(profileRepository.updateProfile(request))
    }
  }.flowOn(Dispatchers.IO)

  private fun checkValidation(request: UpdateProfileRequest): Boolean {
    var isValid = true
    if (request.name.isEmpty()) {
//      request.validation.nameError.set(Constants.EMPTY)
      isValid = false
    }
    if (request.phone.isEmpty()) {
//      request.validation.phoneError.set(Constants.EMPTY)
      isValid = false
    }
    if (request.city_id.isEmpty()) {
//      request.validation.cityError.set(Constants.EMPTY)
      isValid = false
    }

    return isValid
  }
}
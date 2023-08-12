package app.te.architecture.domain.profile.use_case

import app.te.architecture.domain.auth.entity.model.UserResponse
import app.te.architecture.domain.profile.entity.UpdateProfileRequest
import app.te.architecture.domain.profile.repository.ProfileRepository
import app.te.architecture.domain.utils.BaseResponse
import app.te.architecture.domain.utils.Resource
import app.te.architecture.presentation.base.utils.Constants
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
  ): Flow<Resource<BaseResponse<UserResponse>>> = flow {
    if (checkValidation(request)) {
      emit(Resource.Loading)
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
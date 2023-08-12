package app.te.architecture.domain.profile.repository

import app.te.architecture.domain.auth.entity.model.UserResponse
import app.te.architecture.domain.profile.entity.UpdateProfileRequest
import app.te.architecture.domain.utils.BaseResponse
import app.te.architecture.domain.utils.Resource

interface ProfileRepository {
  suspend fun getProfile(): Resource<BaseResponse<UserResponse>>
  suspend fun updateProfile(request: UpdateProfileRequest): Resource<BaseResponse<UserResponse>>
}
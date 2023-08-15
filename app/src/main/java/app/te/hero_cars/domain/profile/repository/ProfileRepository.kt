package app.te.hero_cars.domain.profile.repository

import te.app.auth.domain.entity.model.UserResponse
import app.te.hero_cars.domain.profile.entity.UpdateProfileRequest
import app.te.network.utils.BaseResponse
import app.te.network.utils.Resource

interface ProfileRepository {
  suspend fun getProfile(): app.te.network.utils.Resource<app.te.network.utils.BaseResponse<UserResponse>>
  suspend fun updateProfile(request: UpdateProfileRequest): app.te.network.utils.Resource<app.te.network.utils.BaseResponse<UserResponse>>
}
package app.te.hero_cars.data.profile.data_source

import app.te.hero_cars.domain.profile.entity.UpdateProfileRequest
import app.te.network.BaseRemoteDataSource
import javax.inject.Inject

class ProfileDataSource @Inject constructor(private val apiService: ProfileServices) :
  BaseRemoteDataSource() {

  suspend fun getProfile() = safeApiCall {
    apiService.getProfile()
  }
 suspend fun updateProfile(request: UpdateProfileRequest) = safeApiCall {
    apiService.updateProfile(request)
  }

}
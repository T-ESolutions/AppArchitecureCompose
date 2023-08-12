package app.te.architecture.data.profile.data_source

import app.te.architecture.data.remote.BaseRemoteDataSource
import app.te.architecture.domain.profile.entity.UpdateProfileRequest
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
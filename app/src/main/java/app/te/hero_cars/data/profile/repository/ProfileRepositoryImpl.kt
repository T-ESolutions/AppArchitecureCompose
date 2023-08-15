package app.te.hero_cars.data.profile.repository

import app.te.hero_cars.domain.profile.entity.UpdateProfileRequest
import app.te.hero_cars.domain.profile.repository.ProfileRepository
import app.te.hero_cars.data.profile.data_source.ProfileDataSource
import app.te.hero_cars.domain.auth.entity.model.UserResponse
import te.app.storage.data_store.AppPreferences
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val remoteDataSource: ProfileDataSource,
    private val appPreferences: AppPreferences
) : ProfileRepository {
    override suspend fun getProfile(): app.te.network.utils.Resource<app.te.network.utils.BaseResponse<UserResponse>> =
        remoteDataSource.getProfile()

    override suspend fun updateProfile(request: UpdateProfileRequest): app.te.network.utils.Resource<app.te.network.utils.BaseResponse<UserResponse>> {
        val response = remoteDataSource.updateProfile(request)
        //TODO UN COMMENT
//        if (response is app.te.network.utils.Resource.Success) {
//            appPreferences.saveUser(response.value.data)
//        }
        return response
    }


}
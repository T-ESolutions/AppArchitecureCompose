package app.te.hero_cars.data.profile.data_source

import app.te.hero_cars.domain.auth.entity.model.UserResponse
import app.te.hero_cars.domain.profile.entity.UpdateProfileRequest
import app.te.network.utils.BaseResponse
import retrofit2.http.*

interface ProfileServices {

    @GET("v1/user/profile")
    suspend fun getProfile(): app.te.network.utils.BaseResponse<UserResponse>

    @POST("v1/user/profile/update")
    suspend fun updateProfile(@Body request: UpdateProfileRequest): app.te.network.utils.BaseResponse<UserResponse>

}
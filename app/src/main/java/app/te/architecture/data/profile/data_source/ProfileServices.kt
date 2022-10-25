package app.te.architecture.data.profile.data_source

import app.te.architecture.domain.auth.entity.model.UserResponse
import app.te.architecture.domain.profile.entity.UpdateProfileRequest
import app.te.architecture.domain.utils.BaseResponse
import retrofit2.http.*

interface ProfileServices {

    @GET("v1/user/profile")
    suspend fun getProfile(): BaseResponse<UserResponse>

    @POST("v1/user/profile/update")
    suspend fun updateProfile(@Body request: UpdateProfileRequest): BaseResponse<UserResponse>

}
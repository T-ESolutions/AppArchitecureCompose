package app.te.hero_cars.data.add_stolen_phone.data_source

import app.te.hero_cars.domain.add_stolen_phone.entity.AddStolenPhoneRequest
import app.te.network.utils.BaseResponse
import okhttp3.MultipartBody
import retrofit2.http.*

interface AddStolenServices {
    @Multipart
    @POST("V1/user/add-stolen-phone")
    suspend fun addStolenRequest(
        @QueryMap map: Map<String, String>,
        @Part image: MultipartBody.Part?
    ): app.te.network.utils.BaseResponse<*>

    @POST("V1/user/add-stolen-phone")
    suspend fun addStolenRequest(
        @Body request: AddStolenPhoneRequest,
    ): app.te.network.utils.BaseResponse<*>

}
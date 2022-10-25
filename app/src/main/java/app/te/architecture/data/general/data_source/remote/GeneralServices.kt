package app.te.architecture.data.general.data_source.remote

import app.te.architecture.domain.general.entity.UpdateFirebaseTokenRequest
import app.te.architecture.domain.general.entity.countries.CityModel
import app.te.architecture.domain.utils.BaseResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface GeneralServices {

    @GET("v1/app/cities")
    suspend fun getCities(): BaseResponse<List<CityModel>>

    @POST("v1/user/update_fcm_token")
    suspend fun updateFirebaseToken(@Body updateFirebaseTokenRequest: UpdateFirebaseTokenRequest): BaseResponse<*>

}
package app.te.hero_cars.data.general.data_source.remote

import app.te.hero_cars.data.general.data_source.dto.countries.CityData
import app.te.hero_cars.domain.general.entity.UpdateFirebaseTokenRequest
import app.te.hero_cars.data.general.data_source.dto.countries.GovernData
import app.te.network.utils.BaseResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface GeneralServices {

    @GET("V1/user/cities")
    suspend fun getCities(
        @Query("page") pageIndex: Int,
        @Query("gov_id") govId: String,
    ): app.te.network.utils.BaseResponse<CityData>

    @GET("V1/user/govs")
    suspend fun getGovern(
        @Query("page") PageIndex: Int,
    ): app.te.network.utils.BaseResponse<GovernData>


    @POST("v1/user/update_fcm_token")
    suspend fun updateFirebaseToken(@Body updateFirebaseTokenRequest: UpdateFirebaseTokenRequest): app.te.network.utils.BaseResponse<*>

}
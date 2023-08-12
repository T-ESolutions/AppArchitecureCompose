package app.te.architecture.data.general.data_source.remote

import app.te.architecture.data.general.data_source.dto.countries.CityData
import app.te.architecture.domain.general.entity.UpdateFirebaseTokenRequest
import app.te.architecture.data.general.data_source.dto.countries.GovernData
import app.te.architecture.domain.utils.BaseResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface GeneralServices {

    @GET("V1/user/cities")
    suspend fun getCities(
        @Query("page") pageIndex: Int,
        @Query("gov_id") govId: String,
    ): BaseResponse<CityData>

    @GET("V1/user/govs")
    suspend fun getGovern(
        @Query("page") PageIndex: Int,
    ): BaseResponse<GovernData>


    @POST("v1/user/update_fcm_token")
    suspend fun updateFirebaseToken(@Body updateFirebaseTokenRequest: UpdateFirebaseTokenRequest): BaseResponse<*>

}
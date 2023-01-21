package app.te.architecture.data.brand_model.data_source

import app.te.architecture.data.brand_model.dto.BrandsDto
import app.te.architecture.domain.utils.BaseResponse
import app.te.architecture.data.brand_model.dto.ModelsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface BrandModelServices {

    @GET("V1/user/brands")
    suspend fun getBrands(
        @Query("page") page: Int,
    ): BaseResponse<BrandsDto>

    @GET("V1/user/modells")
    suspend fun getModels(
        @Query("page") page: Int,
        @Query("brand_id") brandId: Int
    ): BaseResponse<ModelsDto>

}
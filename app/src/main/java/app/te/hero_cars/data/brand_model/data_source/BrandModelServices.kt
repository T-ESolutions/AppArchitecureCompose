package app.te.hero_cars.data.brand_model.data_source

import app.te.hero_cars.data.brand_model.dto.BrandsDto
import app.te.network.utils.BaseResponse
import app.te.hero_cars.data.brand_model.dto.ModelsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface BrandModelServices {

    @GET("V1/user/brands")
    suspend fun getBrands(
        @Query("page") page: Int,
    ): app.te.network.utils.BaseResponse<BrandsDto>

    @GET("V1/user/modells")
    suspend fun getModels(
        @Query("page") page: Int,
        @Query("brand_id") brandId: Int
    ): app.te.network.utils.BaseResponse<ModelsDto>

}
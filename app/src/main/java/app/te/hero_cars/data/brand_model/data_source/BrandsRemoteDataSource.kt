package app.te.hero_cars.data.brand_model.data_source

import app.te.network.BaseRemoteDataSource
import javax.inject.Inject

class BrandsRemoteDataSource @Inject constructor(private val apiService: BrandModelServices) :
    app.te.network.BaseRemoteDataSource() {

    suspend fun getBrands(PageIndex: Int) = safeApiCall {
        apiService.getBrands(PageIndex)
    }

    suspend fun getModels(PageIndex: Int, brandId: Int) = safeApiCall {
        apiService.getModels(PageIndex, brandId)
    }

}
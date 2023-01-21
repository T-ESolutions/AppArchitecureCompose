package app.te.architecture.data.brand_model.data_source

import app.te.architecture.data.remote.BaseRemoteDataSource
import javax.inject.Inject

class BrandsRemoteDataSource @Inject constructor(private val apiService: BrandModelServices) :
    BaseRemoteDataSource() {

    suspend fun getBrands(PageIndex: Int) = safeApiCall {
        apiService.getBrands(PageIndex)
    }

    suspend fun getModels(PageIndex: Int, brandId: Int) = safeApiCall {
        apiService.getModels(PageIndex, brandId)
    }

}
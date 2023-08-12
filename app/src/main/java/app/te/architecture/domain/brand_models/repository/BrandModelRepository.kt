package app.te.architecture.domain.brand_models.repository

import androidx.paging.PagingData
import app.te.architecture.data.home.dto.Brand
import app.te.architecture.data.home.dto.Model
import kotlinx.coroutines.flow.Flow


interface BrandModelRepository {
    suspend fun getBrands(): Flow<PagingData<Brand>>
    suspend fun getModels(brandId: Int): Flow<PagingData<Model>>
}
package app.te.hero_cars.domain.brand_models.repository

import androidx.paging.PagingData
import app.te.hero_cars.data.home.dto.Brand
import app.te.hero_cars.data.home.dto.Model
import kotlinx.coroutines.flow.Flow


interface BrandModelRepository {
    suspend fun getBrands(): Flow<PagingData<Brand>>
    suspend fun getModels(brandId: Int): Flow<PagingData<Model>>
}
package app.te.architecture.data.brand_model.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import app.te.architecture.data.brand_model.data_source.BrandsRemoteDataSource
import app.te.architecture.data.home.dto.Brand
import app.te.architecture.data.home.dto.Model
import app.te.architecture.data.remote.AbstractPagingSource
import app.te.architecture.domain.brand_models.repository.BrandModelRepository
import app.te.architecture.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BrandModelRepositoryImpl @Inject constructor(
    private val remoteDataSource: BrandsRemoteDataSource
) : BrandModelRepository {

    override suspend fun getBrands(): Flow<PagingData<Brand>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = {
                object : AbstractPagingSource<Brand>() {

                    override suspend fun fetchData(
                        pageSize: Int,
                        PageIndex: Int
                    ): List<Brand> {
                        val result = remoteDataSource.getBrands(PageIndex)
                        var items = listOf<Brand>()
                        if (result is Resource.Success) {
                            nextPage = result.value.data.links.next
                            items = result.value.data.brand
                        }
                        return items
                    }

                    override fun hasNextPages(): Boolean {
                        return nextPage != null
                    }
                }.getPagingSource()
            }
        ).flow
    }

    override suspend fun getModels(brandId: Int): Flow<PagingData<Model>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = {
                object : AbstractPagingSource<Model>() {

                    override suspend fun fetchData(
                        pageSize: Int,
                        PageIndex: Int
                    ): List<Model> {
                        val result = remoteDataSource.getModels(PageIndex,brandId)
                        var items = listOf<Model>()
                        if (result is Resource.Success) {
                            nextPage = result.value.data.links.next
                            items = result.value.data.models
                        }

                        return items
                    }

                    override fun hasNextPages(): Boolean {
                        return nextPage != null
                    }
                }.getPagingSource()
            }
        ).flow
    }
}
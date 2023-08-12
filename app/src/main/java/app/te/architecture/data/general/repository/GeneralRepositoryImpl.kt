package app.te.architecture.data.general.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import app.te.architecture.data.general.data_source.remote.GeneralRemoteDataSource
import app.te.architecture.domain.general.entity.UpdateFirebaseTokenRequest
import app.te.architecture.data.general.data_source.dto.countries.CityModel
import app.te.architecture.data.general.data_source.dto.countries.Government
import app.te.architecture.data.remote.AbstractPagingSource
import app.te.architecture.domain.general.repository.GeneralRepository
import app.te.architecture.domain.utils.BaseResponse
import app.te.architecture.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GeneralRepositoryImpl @Inject constructor(
    private val remoteDataSource: GeneralRemoteDataSource
) : GeneralRepository {

    override suspend fun getGovernments(): Flow<PagingData<Government>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = {
                object : AbstractPagingSource<Government>() {

                    override suspend fun fetchData(
                        pageSize: Int,
                        PageIndex: Int
                    ): List<Government> {
                        val result = remoteDataSource.getGovern(PageIndex)
                        var items = listOf<Government>()
                        if (result is Resource.Success) {
                            nextPage = result.value.data.links.next
                            items = result.value.data.government
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

    override suspend fun getCities(govId: String): Flow<PagingData<CityModel>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = {
                object : AbstractPagingSource<CityModel>() {

                    override suspend fun fetchData(
                        pageSize: Int,
                        PageIndex: Int
                    ): List<CityModel> {
                        val result = remoteDataSource.getCities(govId,PageIndex)
                        var items = listOf<CityModel>()
                        if (result is Resource.Success) {
                            nextPage = result.value.data.links.next
                            items = result.value.data.city
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

    override suspend fun updateFirebaseTokenRequest(updateFirebaseTokenRequest: UpdateFirebaseTokenRequest): Resource<BaseResponse<*>> =
        remoteDataSource.updateFirebaseToken(updateFirebaseTokenRequest)
}
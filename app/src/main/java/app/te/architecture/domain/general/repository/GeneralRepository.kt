package app.te.architecture.domain.general.repository

import androidx.paging.PagingData
import app.te.architecture.domain.general.entity.UpdateFirebaseTokenRequest
import app.te.architecture.data.general.data_source.dto.countries.CityModel
import app.te.architecture.data.general.data_source.dto.countries.Government
import app.te.architecture.domain.utils.BaseResponse
import app.te.architecture.domain.utils.Resource
import kotlinx.coroutines.flow.Flow


interface GeneralRepository {
    suspend fun getGovernments(): Flow<PagingData<Government>>
    suspend fun getCities(govId: String): Flow<PagingData<CityModel>>
    suspend fun updateFirebaseTokenRequest(updateFirebaseTokenRequest: UpdateFirebaseTokenRequest): Resource<BaseResponse<*>>
}
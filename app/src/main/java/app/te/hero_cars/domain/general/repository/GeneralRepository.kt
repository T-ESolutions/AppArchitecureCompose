package app.te.hero_cars.domain.general.repository

import androidx.paging.PagingData
import app.te.hero_cars.domain.general.entity.UpdateFirebaseTokenRequest
import app.te.hero_cars.data.general.data_source.dto.countries.CityModel
import app.te.hero_cars.data.general.data_source.dto.countries.Government
import app.te.network.utils.BaseResponse
import app.te.network.utils.Resource
import kotlinx.coroutines.flow.Flow


interface GeneralRepository {
    suspend fun getGovernments(): Flow<PagingData<Government>>
    suspend fun getCities(govId: String): Flow<PagingData<CityModel>>
    suspend fun updateFirebaseTokenRequest(updateFirebaseTokenRequest: UpdateFirebaseTokenRequest): app.te.network.utils.Resource<app.te.network.utils.BaseResponse<*>>
}
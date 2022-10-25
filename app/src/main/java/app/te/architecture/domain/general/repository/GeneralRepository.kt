package app.te.architecture.domain.general.repository

import app.te.architecture.domain.general.entity.UpdateFirebaseTokenRequest
import app.te.architecture.domain.general.entity.countries.CityModel
import app.te.architecture.domain.utils.BaseResponse
import app.te.architecture.domain.utils.Resource


interface GeneralRepository {
    suspend fun getCities(): Resource<BaseResponse<List<CityModel>>>
    suspend fun updateFirebaseTokenRequest(updateFirebaseTokenRequest: UpdateFirebaseTokenRequest): Resource<BaseResponse<*>>
}
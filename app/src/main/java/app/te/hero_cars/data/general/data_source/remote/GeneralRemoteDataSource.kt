package app.te.hero_cars.data.general.data_source.remote

import app.te.network.BaseRemoteDataSource
import app.te.hero_cars.domain.general.entity.UpdateFirebaseTokenRequest
import javax.inject.Inject

class GeneralRemoteDataSource @Inject constructor(private val apiService: GeneralServices) :
    app.te.network.BaseRemoteDataSource() {
    suspend fun getCities(govId: String, pageIndex: Int) = safeApiCall {
        apiService.getCities(pageIndex, govId)
    }

    suspend fun getGovern(pageIndex: Int) = safeApiCall {
        apiService.getGovern(pageIndex)
    }

    suspend fun updateFirebaseToken(updateFirebaseTokenRequest: UpdateFirebaseTokenRequest) =
        safeApiCall {
            apiService.updateFirebaseToken(updateFirebaseTokenRequest)
        }

}
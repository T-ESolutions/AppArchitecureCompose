package app.te.architecture.data.general.data_source.remote

import app.te.architecture.data.remote.BaseRemoteDataSource
import app.te.architecture.domain.general.entity.UpdateFirebaseTokenRequest
import javax.inject.Inject

class GeneralRemoteDataSource @Inject constructor(private val apiService: GeneralServices) :
    BaseRemoteDataSource() {
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
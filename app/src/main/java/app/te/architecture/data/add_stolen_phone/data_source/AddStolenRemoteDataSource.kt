package app.te.architecture.data.add_stolen_phone.data_source

import app.te.architecture.data.remote.BaseRemoteDataSource
import app.te.architecture.domain.add_stolen_phone.entity.AddStolenPhoneRequest
import javax.inject.Inject

class AddStolenRemoteDataSource @Inject constructor(
    private val apiService: AddStolenServices
) : BaseRemoteDataSource() {

    suspend fun addStolenRequest(request: AddStolenPhoneRequest) = safeApiCall {
        if (request.image.isNotEmpty())
            apiService.addStolenRequest(getParameters(request), request.image[0])
        else
            apiService.addStolenRequest(request)
    }

}
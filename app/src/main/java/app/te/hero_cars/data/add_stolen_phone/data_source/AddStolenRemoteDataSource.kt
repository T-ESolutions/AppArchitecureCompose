package app.te.hero_cars.data.add_stolen_phone.data_source

import app.te.network.BaseRemoteDataSource
import app.te.hero_cars.domain.add_stolen_phone.entity.AddStolenPhoneRequest
import javax.inject.Inject

class AddStolenRemoteDataSource @Inject constructor(
    private val apiService: AddStolenServices
) : app.te.network.BaseRemoteDataSource() {

    suspend fun addStolenRequest(request: AddStolenPhoneRequest) = safeApiCall {
        if (request.image.isNotEmpty())
            apiService.addStolenRequest(getParameters(request), request.image[0])
        else
            apiService.addStolenRequest(request)
    }

}
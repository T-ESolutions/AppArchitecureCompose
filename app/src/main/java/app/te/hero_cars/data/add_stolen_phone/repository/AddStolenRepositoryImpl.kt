package app.te.hero_cars.data.add_stolen_phone.repository

import app.te.hero_cars.data.add_stolen_phone.data_source.AddStolenRemoteDataSource
import app.te.hero_cars.domain.add_stolen_phone.entity.AddStolenPhoneRequest
import app.te.hero_cars.domain.add_stolen_phone.repository.AddStolenRepository
import app.te.network.utils.BaseResponse
import app.te.network.utils.Resource
import javax.inject.Inject

class AddStolenRepositoryImpl @Inject constructor(
    private val remoteDataSource: AddStolenRemoteDataSource
) : AddStolenRepository {

    override suspend fun addStolen(request: AddStolenPhoneRequest): app.te.network.utils.Resource<app.te.network.utils.BaseResponse<*>> =
        remoteDataSource.addStolenRequest(request)

}


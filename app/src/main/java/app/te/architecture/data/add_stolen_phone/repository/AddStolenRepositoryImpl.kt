package app.te.architecture.data.add_stolen_phone.repository

import app.te.architecture.data.add_stolen_phone.data_source.AddStolenRemoteDataSource
import app.te.architecture.domain.add_stolen_phone.entity.AddStolenPhoneRequest
import app.te.architecture.domain.add_stolen_phone.repository.AddStolenRepository
import app.te.architecture.domain.utils.BaseResponse
import app.te.architecture.domain.utils.Resource
import javax.inject.Inject

class AddStolenRepositoryImpl @Inject constructor(
    private val remoteDataSource: AddStolenRemoteDataSource
) : AddStolenRepository {

    override suspend fun addStolen(request: AddStolenPhoneRequest): Resource<BaseResponse<*>> =
        remoteDataSource.addStolenRequest(request)

}


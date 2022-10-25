package app.te.architecture.data.general.repository

import app.te.architecture.data.general.data_source.remote.GeneralRemoteDataSource
import app.te.architecture.domain.general.entity.UpdateFirebaseTokenRequest
import app.te.architecture.domain.general.entity.countries.CityModel
import app.te.architecture.domain.general.repository.GeneralRepository
import app.te.architecture.domain.utils.BaseResponse
import app.te.architecture.domain.utils.Resource
import javax.inject.Inject

class GeneralRepositoryImpl @Inject constructor(
    private val remoteDataSource: GeneralRemoteDataSource
) : GeneralRepository {

    override suspend fun getCities(): Resource<BaseResponse<List<CityModel>>> =
        remoteDataSource.getCities()

    override suspend fun updateFirebaseTokenRequest(updateFirebaseTokenRequest: UpdateFirebaseTokenRequest): Resource<BaseResponse<*>> =
        remoteDataSource.updateFirebaseToken(updateFirebaseTokenRequest)
}
package app.te.architecture.data.home.repository

import app.te.architecture.data.home.data_source.remote.HomeRemoteDataSource
import app.te.architecture.data.home.dto.SearchData
import app.te.architecture.domain.home.models.SerialSearchDM
import app.te.architecture.domain.home.repository.HomeRepository
import app.te.architecture.domain.utils.BaseResponse
import app.te.architecture.domain.utils.Resource
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val remoteDataSource: HomeRemoteDataSource) :
    HomeRepository {

    override suspend fun searchForPhone(serial: String): Resource<BaseResponse<SerialSearchDM>> {
        val response = remoteDataSource.searchForPhone(serial)
        val newSearchDM: SerialSearchDM
        if (response is Resource.Success) {
            newSearchDM = mapSearchDataDto(response.value.data)
            return Resource.Success(
                BaseResponse(
                    data = newSearchDM,
                    status = response.value.status,
                    message = response.value.message
                )
            )
        } else if (response is Resource.Failure)
            return Resource.Failure(
                failureStatus = response.failureStatus
            )
        return Resource.Default
    }

    private fun mapSearchDataDto(searchData: SearchData): SerialSearchDM =
        SerialSearchDM(
            added_by = searchData.added_by,
            id = searchData.id,
            image = searchData.image,
            notes = searchData.notes,
            serial = searchData.serial,
            modell_name = searchData.modell_name,
            brand_name = searchData.brand_name,
            city_name = searchData.city_name,
            gov_name = searchData.gov_name,
            phone = searchData.phone
        )
}
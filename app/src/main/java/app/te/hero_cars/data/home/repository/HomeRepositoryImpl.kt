package app.te.hero_cars.data.home.repository

import app.te.hero_cars.data.home.data_source.remote.HomeRemoteDataSource
import app.te.hero_cars.data.home.dto.SearchData
import app.te.hero_cars.domain.home.models.SerialSearchDM
import app.te.hero_cars.domain.home.repository.HomeRepository
import app.te.network.utils.BaseResponse
import app.te.network.utils.Resource
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val remoteDataSource: HomeRemoteDataSource) :
    HomeRepository {

    override suspend fun searchForPhone(serial: String): app.te.network.utils.Resource<app.te.network.utils.BaseResponse<SerialSearchDM>> {
        val response = remoteDataSource.searchForPhone(serial)
        val newSearchDM: SerialSearchDM
        if (response is app.te.network.utils.Resource.Success) {
            newSearchDM = mapSearchDataDto(response.value.data)
            return app.te.network.utils.Resource.Success(
                app.te.network.utils.BaseResponse(
                    data = newSearchDM,
                    status = response.value.status,
                    message = response.value.message
                )
            )
        } else if (response is app.te.network.utils.Resource.Failure)
            return app.te.network.utils.Resource.Failure(
                failureStatus = response.failureStatus
            )
        return app.te.network.utils.Resource.Default
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
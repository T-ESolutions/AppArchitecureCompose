package app.te.hero_cars.data.settings.repository

import app.te.hero_cars.data.settings.data_source.remote.SettingsRemoteDataSource
import app.te.hero_cars.domain.settings.models.AboutData
import app.te.hero_cars.domain.settings.models.ContactUs
import app.te.hero_cars.domain.settings.models.Teams
import app.te.hero_cars.domain.settings.repository.SettingsRepository
import app.te.network.utils.BaseResponse
import app.te.network.utils.Resource
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(private val remoteDataSource: SettingsRemoteDataSource) :
  SettingsRepository {
  override suspend fun about(page: String): app.te.network.utils.Resource<app.te.network.utils.BaseResponse<AboutData>> =
    remoteDataSource.about(page)

  override suspend fun getTeam(): app.te.network.utils.Resource<app.te.network.utils.BaseResponse<List<Teams>>> = remoteDataSource.getTeam()
  override suspend fun getContact(): app.te.network.utils.Resource<app.te.network.utils.BaseResponse<List<ContactUs>>> =
    remoteDataSource.getContacts()


}
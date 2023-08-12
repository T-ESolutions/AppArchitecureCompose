package app.te.architecture.data.settings.repository

import app.te.architecture.data.settings.data_source.remote.SettingsRemoteDataSource
import app.te.architecture.domain.settings.models.AboutData
import app.te.architecture.domain.settings.models.ContactUs
import app.te.architecture.domain.settings.models.Teams
import app.te.architecture.domain.settings.repository.SettingsRepository
import app.te.architecture.domain.utils.BaseResponse
import app.te.architecture.domain.utils.Resource
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(private val remoteDataSource: SettingsRemoteDataSource) :
  SettingsRepository {
  override suspend fun about(page: String): Resource<BaseResponse<AboutData>> =
    remoteDataSource.about(page)

  override suspend fun getTeam(): Resource<BaseResponse<List<Teams>>> = remoteDataSource.getTeam()
  override suspend fun getContact(): Resource<BaseResponse<List<ContactUs>>> =
    remoteDataSource.getContacts()


}
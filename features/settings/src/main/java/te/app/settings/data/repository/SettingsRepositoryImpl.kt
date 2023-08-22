package te.app.settings.data.repository

import app.te.network.utils.BaseResponse
import app.te.network.utils.Resource
import te.app.settings.data.data_source.remote.SettingsRemoteDataSource
import te.app.settings.domain.models.AboutData
import te.app.settings.domain.models.ContactUs
import te.app.settings.domain.models.Teams
import te.app.settings.domain.repository.SettingsRepository
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(private val remoteDataSource: SettingsRemoteDataSource) :
  SettingsRepository {
  override suspend fun about(page: String): Resource<BaseResponse<AboutData>> =
    remoteDataSource.about(page)

  override suspend fun getTeam(): Resource<BaseResponse<List<Teams>>> = remoteDataSource.getTeam()
  override suspend fun getContact(): Resource<BaseResponse<List<ContactUs>>> =
    remoteDataSource.getContacts()


}
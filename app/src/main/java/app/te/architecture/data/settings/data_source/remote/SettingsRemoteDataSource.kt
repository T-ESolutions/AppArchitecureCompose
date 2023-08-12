package app.te.architecture.data.settings.data_source.remote

import app.te.architecture.data.remote.BaseRemoteDataSource
import javax.inject.Inject

class SettingsRemoteDataSource @Inject constructor(private val apiService: SettingsServices) :
  BaseRemoteDataSource() {

  suspend fun about(page: String) = safeApiCall {
    apiService.about(page)
  }

  suspend fun getTeam() = safeApiCall {
    apiService.teams()
  }

  suspend fun getContacts() = safeApiCall {
    apiService.getContacts()
  }

}
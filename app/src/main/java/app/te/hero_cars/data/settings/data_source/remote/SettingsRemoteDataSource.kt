package app.te.hero_cars.data.settings.data_source.remote

import app.te.network.BaseRemoteDataSource
import javax.inject.Inject

class SettingsRemoteDataSource @Inject constructor(private val apiService: SettingsServices) :
  app.te.network.BaseRemoteDataSource() {

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
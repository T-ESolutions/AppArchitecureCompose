package app.te.hero_cars.data.home.data_source.remote

import app.te.network.BaseRemoteDataSource
import javax.inject.Inject

class HomeRemoteDataSource @Inject constructor(private val apiService: HomeServices) :
  app.te.network.BaseRemoteDataSource() {

  suspend fun searchForPhone(serial: String) = safeApiCall {
    apiService.searchForPhone(serial)
  }
}
package app.te.architecture.data.home.data_source.remote

import app.te.architecture.data.remote.BaseRemoteDataSource
import javax.inject.Inject

class HomeRemoteDataSource @Inject constructor(private val apiService: HomeServices) :
  BaseRemoteDataSource() {

  suspend fun searchForPhone(serial: String) = safeApiCall {
    apiService.searchForPhone(serial)
  }
}
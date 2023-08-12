package app.te.architecture.data.intro.data_source

import app.te.architecture.data.remote.BaseRemoteDataSource
import javax.inject.Inject

class IntroRemoteDataSource @Inject constructor(private val apiService: IntroServices) :
  BaseRemoteDataSource() {

  suspend fun intro() = safeApiCall {
    apiService.intro()
  }
}
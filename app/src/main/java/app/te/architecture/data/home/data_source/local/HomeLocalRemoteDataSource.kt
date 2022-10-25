package app.te.architecture.data.home.data_source.local

import app.te.architecture.domain.home.models.HomeMainData
import javax.inject.Inject

class HomeLocalRemoteDataSource @Inject constructor(private val homeDao: HomeDao) {

  fun homeStudentLocal() = homeDao.getNews()
  suspend fun insertHomeStudent(homeMainData: HomeMainData)=homeDao.insertHomeData(homeMainData)
  fun homeStudentDelete() = homeDao.deleteHomeData()

}
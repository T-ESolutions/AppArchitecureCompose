package app.te.architecture.data.home.repository.local

import app.te.architecture.data.home.data_source.local.HomeLocalRemoteDataSource
import app.te.architecture.domain.home.models.HomeMainData
import app.te.architecture.domain.home.repository.local.HomeLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeLocalRepositoryImpl @Inject constructor(private val homeLocalRemoteDataSource: HomeLocalRemoteDataSource) :
  HomeLocalRepository {
  override fun studentHomeLocal(): Flow<HomeMainData> =
    homeLocalRemoteDataSource.homeStudentLocal()

  override suspend fun insertStudentHomeLocal(homeMainData: HomeMainData) =
    homeLocalRemoteDataSource.insertHomeStudent(homeMainData)
  override suspend fun deleteAll()  = homeLocalRemoteDataSource.homeStudentDelete()

}
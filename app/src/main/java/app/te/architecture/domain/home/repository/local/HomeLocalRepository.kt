package app.te.architecture.domain.home.repository.local

import app.te.architecture.domain.home.models.HomeMainData
import kotlinx.coroutines.flow.Flow


interface HomeLocalRepository {
  fun studentHomeLocal(): Flow<HomeMainData>
  suspend fun insertStudentHomeLocal(homeMainData: HomeMainData)
  suspend fun deleteAll()

}
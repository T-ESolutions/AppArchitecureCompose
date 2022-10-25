package app.te.architecture.data.home.data_source.local

import androidx.room.*
import app.te.architecture.domain.home.models.HomeMainData
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeDao {
  @Query("Select * from HomeMainData")
  fun getNews(): Flow<HomeMainData>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertHomeData(homeMainData: HomeMainData)
  @Query("DELETE from HomeMainData")
  fun deleteHomeData()

}
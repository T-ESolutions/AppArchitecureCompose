package app.te.architecture.core

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.te.architecture.data.home.data_source.local.HomeDao
import app.te.architecture.domain.home.models.HomeMainData
import app.te.architecture.domain.utils.Converters

@Database(
  entities = [HomeMainData::class],
  version = 2, exportSchema = false
)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
  abstract val getHomeDao: HomeDao
}


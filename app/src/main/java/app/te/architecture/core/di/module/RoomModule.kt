package app.te.architecture.core.di.module

import android.content.Context
import androidx.room.Room
import app.te.architecture.core.AppDatabase
import app.te.architecture.data.home.data_source.local.HomeLocalRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
  @Provides
  @Singleton
  fun provideMyDB(@ApplicationContext context: Context): AppDatabase {
    return Room.databaseBuilder(
      context,
      AppDatabase::class.java,
      "BuildConfig"
    ).build()
  }

  @Provides
  @Singleton
  fun provideHomeLocalRepository(db: AppDatabase): HomeLocalRemoteDataSource {
    return HomeLocalRemoteDataSource(db.getHomeDao)
  }

}




package te.app.storage.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import te.app.storage.data.repository.StorageRepositoryImpl
import te.app.storage.data_store.AppPreferences
import te.app.storage.domain.repository.StorageRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideStorageRepository(
        appPreferences: AppPreferences
    ): StorageRepository = StorageRepositoryImpl(appPreferences)

}
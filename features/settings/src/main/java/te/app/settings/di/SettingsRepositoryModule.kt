package te.app.settings.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import te.app.settings.data.data_source.remote.SettingsRemoteDataSource
import te.app.settings.data.repository.SettingsRepositoryImpl
import te.app.settings.domain.repository.SettingsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SettingsRepositoryModule {
    @Provides
    @Singleton
    fun provideSettingsRepository(
        remoteDataSource: SettingsRemoteDataSource
    ): SettingsRepository = SettingsRepositoryImpl(remoteDataSource)

}
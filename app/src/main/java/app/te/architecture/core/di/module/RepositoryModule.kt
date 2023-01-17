package app.te.architecture.core.di.module

import app.te.architecture.data.account.data_source.remote.AccountRemoteDataSource
import app.te.architecture.data.account.repository.AccountRepositoryImpl
import app.te.architecture.data.auth.data_source.remote.AuthRemoteDataSource
import app.te.architecture.data.auth.repository.AuthRepositoryImpl
import app.te.architecture.data.general.data_source.remote.GeneralRemoteDataSource
import app.te.architecture.data.general.repository.GeneralRepositoryImpl
import app.te.architecture.data.home.data_source.remote.HomeRemoteDataSource
import app.te.architecture.data.home.repository.HomeRepositoryImpl
import app.te.architecture.data.intro.data_source.IntroRemoteDataSource
import app.te.architecture.data.intro.repository.IntroRepositoryImpl
import app.te.architecture.data.local.preferences.AppPreferences
import app.te.architecture.data.profile.data_source.ProfileDataSource
import app.te.architecture.data.profile.repository.ProfileRepositoryImpl
import app.te.architecture.data.settings.data_source.remote.SettingsRemoteDataSource
import app.te.architecture.data.settings.repository.SettingsRepositoryImpl
import app.te.architecture.domain.account.repository.AccountRepository
import app.te.architecture.domain.auth.repository.AuthRepository
import app.te.architecture.domain.general.repository.GeneralRepository
import app.te.architecture.domain.home.repository.HomeRepository
import app.te.architecture.domain.intro.repository.IntroRepository
import app.te.architecture.domain.profile.repository.ProfileRepository
import app.te.architecture.domain.settings.repository.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideGeneralRepository(
        remoteDataSource: GeneralRemoteDataSource
    ): GeneralRepository =
        GeneralRepositoryImpl(remoteDataSource)

    @Provides
    @Singleton
    fun provideAuthRepository(
        remoteDataSource: AuthRemoteDataSource,
        appPreferences: AppPreferences
    ): AuthRepository = AuthRepositoryImpl(remoteDataSource,appPreferences)


    @Provides
    @Singleton
    fun provideAccountRepository(
        remoteDataSource: AccountRemoteDataSource,
        appPreferences: AppPreferences
    ): AccountRepository = AccountRepositoryImpl(remoteDataSource, appPreferences)


    @Provides
    @Singleton
    fun provideHomeRepository(
        remoteDataSource: HomeRemoteDataSource
    ): HomeRepository = HomeRepositoryImpl(remoteDataSource)


    @Provides
    @Singleton
    fun provideIntroRepository(
        remoteDataSource: IntroRemoteDataSource
    ): IntroRepository = IntroRepositoryImpl(remoteDataSource)

    @Provides
    @Singleton
    fun provideSettingsRepository(
        remoteDataSource: SettingsRemoteDataSource
    ): SettingsRepository = SettingsRepositoryImpl(remoteDataSource)

    @Provides
    @Singleton
    fun provideUpdateProfileRepository(
        remoteDataSource: ProfileDataSource,
        appPreferences: AppPreferences
    ): ProfileRepository = ProfileRepositoryImpl(remoteDataSource,appPreferences)

}
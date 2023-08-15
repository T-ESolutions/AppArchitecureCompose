package app.te.hero_cars.core.di.module

import app.te.hero_cars.data.account.data_source.remote.AccountRemoteDataSource
import app.te.hero_cars.data.account.repository.AccountRepositoryImpl
import app.te.hero_cars.data.add_stolen_phone.data_source.AddStolenRemoteDataSource
import app.te.hero_cars.data.add_stolen_phone.repository.AddStolenRepositoryImpl
import te.app.auth.data.data_source.remote.AuthRemoteDataSource
import te.app.auth.data.repository.AuthRepositoryImpl
import app.te.hero_cars.data.brand_model.data_source.BrandsRemoteDataSource
import app.te.hero_cars.data.brand_model.repository.BrandModelRepositoryImpl
import app.te.hero_cars.data.general.data_source.remote.GeneralRemoteDataSource
import app.te.hero_cars.data.general.repository.GeneralRepositoryImpl
import app.te.hero_cars.data.home.data_source.remote.HomeRemoteDataSource
import app.te.hero_cars.data.home.repository.HomeRepositoryImpl
import app.te.hero_cars.data.intro.data_source.IntroRemoteDataSource
import app.te.hero_cars.data.intro.repository.IntroRepositoryImpl
import app.te.hero_cars.data.profile.data_source.ProfileDataSource
import app.te.hero_cars.data.profile.repository.ProfileRepositoryImpl
import app.te.hero_cars.data.settings.data_source.remote.SettingsRemoteDataSource
import app.te.hero_cars.data.settings.repository.SettingsRepositoryImpl
import app.te.hero_cars.domain.account.repository.AccountRepository
import app.te.hero_cars.domain.add_stolen_phone.repository.AddStolenRepository
import te.app.auth.domain.repository.AuthRepository
import app.te.hero_cars.domain.brand_models.repository.BrandModelRepository
import app.te.hero_cars.domain.general.repository.GeneralRepository
import app.te.hero_cars.domain.home.repository.HomeRepository
import app.te.hero_cars.domain.intro.repository.IntroRepository
import app.te.hero_cars.domain.profile.repository.ProfileRepository
import app.te.hero_cars.domain.settings.repository.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import te.app.storage.data_store.AppPreferences
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

    @Provides
    @Singleton
    fun provideAddStolenRepository(
        remoteDataSource: AddStolenRemoteDataSource
    ): AddStolenRepository = AddStolenRepositoryImpl(remoteDataSource)

    @Provides
    @Singleton
    fun provideBrandModelRepository(
        remoteDataSource: BrandsRemoteDataSource
    ): BrandModelRepository = BrandModelRepositoryImpl(remoteDataSource)

}
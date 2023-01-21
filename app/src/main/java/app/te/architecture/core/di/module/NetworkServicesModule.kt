package app.te.architecture.core.di.module

import app.te.architecture.data.account.data_source.remote.AccountServices
import app.te.architecture.data.add_stolen_phone.data_source.AddStolenServices
import app.te.architecture.data.auth.data_source.remote.AuthServices
import app.te.architecture.data.brand_model.data_source.BrandModelServices
import app.te.architecture.data.general.data_source.remote.GeneralServices
import app.te.architecture.data.home.data_source.remote.HomeServices
import app.te.architecture.data.intro.data_source.IntroServices
import app.te.architecture.data.profile.data_source.ProfileServices
import app.te.architecture.data.settings.data_source.remote.SettingsServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkServicesModule {

    @Provides
    @Singleton
    fun provideAuthServices(retrofit: Retrofit): AuthServices =
        retrofit.create(AuthServices::class.java)

    @Provides
    @Singleton
    fun provideProfileServices(retrofit: Retrofit): ProfileServices =
        retrofit.create(ProfileServices::class.java)

    @Provides
    @Singleton
    fun provideAccountServices(retrofit: Retrofit): AccountServices =
        retrofit.create(AccountServices::class.java)

    @Provides
    @Singleton
    fun provideGeneralServices(retrofit: Retrofit): GeneralServices =
        retrofit.create(GeneralServices::class.java)


    @Provides
    @Singleton
    fun provideHomeServices(retrofit: Retrofit): HomeServices =
        retrofit.create(HomeServices::class.java)

    @Provides
    @Singleton
    fun provideIntroServices(retrofit: Retrofit): IntroServices =
        retrofit.create(IntroServices::class.java)

    @Provides
    @Singleton
    fun provideSettingsServices(retrofit: Retrofit): SettingsServices =
        retrofit.create(SettingsServices::class.java)

    @Provides
    @Singleton
    fun provideAddStolenServices(retrofit: Retrofit): AddStolenServices =
        retrofit.create(AddStolenServices::class.java)

    @Provides
    @Singleton
    fun provideBrandModelServices(retrofit: Retrofit): BrandModelServices =
        retrofit.create(BrandModelServices::class.java)

}
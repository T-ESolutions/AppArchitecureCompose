package te.app.settings.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import te.app.settings.data.data_source.remote.SettingsServices
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object NetworkServiceModule {
    @Provides
    @Singleton
    fun provideSettingsServices(retrofit: Retrofit): SettingsServices =
        retrofit.create(SettingsServices::class.java)

}
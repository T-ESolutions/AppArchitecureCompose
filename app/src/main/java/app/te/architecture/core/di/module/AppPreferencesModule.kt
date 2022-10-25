package app.te.architecture.core.di.module

import android.content.Context
import app.te.architecture.core.notifications.handler.NotificationHandler
import app.te.architecture.data.local.preferences.AppPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppPreferencesModule {

    @Provides
    @Singleton
    fun providePreferences(@ApplicationContext context: Context) = AppPreferences(context)

    @Provides
    @Singleton
    fun providePushNotificationHandler(@ApplicationContext context: Context) = NotificationHandler(context)

}
package app.te.hero_cars.core.di.module

import android.content.Context
import app.te.hero_cars.core.notifications.handler.NotificationHandler
import app.te.navigation.NavigationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppsModule {

    @Provides
    @Singleton
    fun providePushNotificationHandler(@ApplicationContext context: Context) =
        NotificationHandler(context)

    @Provides
    @Singleton
    fun providesNavigationManager() =
        NavigationManager()


}
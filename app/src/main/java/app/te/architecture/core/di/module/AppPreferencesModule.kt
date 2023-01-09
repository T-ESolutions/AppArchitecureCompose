package app.te.architecture.core.di.module

import android.content.Context
import app.te.architecture.core.notifications.handler.NotificationHandler
import app.te.architecture.data.local.preferences.AppPreferences
import app.te.architecture.presentation.base.validation_usecase.ValidateEmail
import app.te.architecture.presentation.base.validation_usecase.ValidateField
import app.te.architecture.presentation.base.validation_usecase.ValidatePassword
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
    fun providePushNotificationHandler(@ApplicationContext context: Context) =
        NotificationHandler(context)

    @Provides
    @Singleton
    fun provideValidateEmail(@ApplicationContext context: Context) =
        ValidateEmail(context)

    @Provides
    @Singleton
    fun provideValidatePassword(@ApplicationContext context: Context) =
        ValidatePassword(context)

    @Provides
    @Singleton
    fun provideValidateField(@ApplicationContext context: Context) =
        ValidateField(context)

}
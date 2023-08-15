package app.te.core.di

import android.content.Context
import app.te.core.validation_usecase.ValidateCheckBox
import app.te.core.validation_usecase.ValidateEmail
import app.te.core.validation_usecase.ValidateField
import app.te.core.validation_usecase.ValidatePassword
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ValidationsModule {

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

    @Provides
    @Singleton
    fun provideValidateCheckBox(@ApplicationContext context: Context) =
        ValidateCheckBox(context)

}
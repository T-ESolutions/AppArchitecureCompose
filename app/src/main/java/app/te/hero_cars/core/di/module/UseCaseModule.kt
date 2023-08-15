package app.te.hero_cars.core.di.module

import app.te.hero_cars.domain.account.use_case.AccountUseCases
import te.app.storage.domain.use_case.CheckFirstTimeUseCase
import te.app.storage.domain.use_case.CheckLoggedInUserUseCase
import te.app.storage.domain.use_case.ConfigUseCase
import app.te.hero_cars.domain.account.use_case.LogOutUseCase
import app.te.hero_cars.domain.account.use_case.SendFirebaseTokenUseCase
import te.app.storage.domain.use_case.SetFirstTimeUseCase
import te.app.storage.domain.use_case.GeneralUseCases
import te.app.storage.domain.use_case.LanguageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideGeneralUseCases(
        checkFirstTimeUseCase: CheckFirstTimeUseCase,
        checkLoggedInUserUseCase: CheckLoggedInUserUseCase,
        setFirstTimeUseCase: SetFirstTimeUseCase,
        configUseCase: ConfigUseCase,
        languageUseCase: LanguageUseCase
    ): GeneralUseCases =
        GeneralUseCases(
            checkFirstTimeUseCase,
            checkLoggedInUserUseCase,
            setFirstTimeUseCase,
            configUseCase,
            languageUseCase
        )

    @Provides
    @Singleton
    fun provideAccountUseCases(
        logOutUseCase: LogOutUseCase,
        sendFirebaseTokenUseCase: SendFirebaseTokenUseCase
    ): AccountUseCases = AccountUseCases(logOutUseCase, sendFirebaseTokenUseCase)

}
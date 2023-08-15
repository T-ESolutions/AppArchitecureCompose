package app.te.hero_cars.domain.general.use_case

import app.te.hero_cars.domain.account.use_case.CheckFirstTimeUseCase
import app.te.hero_cars.domain.account.use_case.CheckLoggedInUserUseCase
import app.te.hero_cars.domain.account.use_case.SetFirstTimeUseCase
import app.te.hero_cars.domain.account.use_case.ConfigUseCase

class GeneralUseCases(
  val checkFirstTimeUseCase: CheckFirstTimeUseCase,
  val checkLoggedInUserUseCase: CheckLoggedInUserUseCase,
  val setFirstTimeUseCase: SetFirstTimeUseCase,
  val configUseCase: ConfigUseCase,
  val languageUseCase: LanguageUseCase
)
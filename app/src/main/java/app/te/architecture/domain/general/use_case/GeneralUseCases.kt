package app.te.architecture.domain.general.use_case

import app.te.architecture.domain.account.use_case.CheckFirstTimeUseCase
import app.te.architecture.domain.account.use_case.CheckLoggedInUserUseCase
import app.te.architecture.domain.account.use_case.SetFirstTimeUseCase
import app.te.architecture.domain.account.use_case.ConfigUseCase

class GeneralUseCases(
  val checkFirstTimeUseCase: CheckFirstTimeUseCase,
  val checkLoggedInUserUseCase: CheckLoggedInUserUseCase,
  val setFirstTimeUseCase: SetFirstTimeUseCase,
  val configUseCase: ConfigUseCase,
  val languageUseCase: LanguageUseCase
)
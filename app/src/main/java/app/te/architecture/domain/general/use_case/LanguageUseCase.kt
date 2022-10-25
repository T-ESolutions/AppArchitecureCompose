package app.te.architecture.domain.general.use_case

import app.te.architecture.domain.account.repository.AccountRepository
import javax.inject.Inject

class LanguageUseCase @Inject constructor(private val accountRepository: AccountRepository) {
  suspend fun invoke() = accountRepository.getLang()
  suspend fun invoke(lang: String) = accountRepository.setLang(lang)
}
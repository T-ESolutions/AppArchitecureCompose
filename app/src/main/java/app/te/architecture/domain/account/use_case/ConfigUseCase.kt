package app.te.architecture.domain.account.use_case

import app.te.architecture.domain.account.repository.AccountRepository
import javax.inject.Inject

class ConfigUseCase @Inject constructor(private val accountRepository: AccountRepository) {
  suspend fun saveSplash(value: String) =
    accountRepository.saveSplash(value)

  suspend fun getSplash() =
    accountRepository.getSplash()

}
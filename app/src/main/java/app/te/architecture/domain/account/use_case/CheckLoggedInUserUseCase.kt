package app.te.architecture.domain.account.use_case

import app.te.architecture.domain.account.repository.AccountRepository
import javax.inject.Inject

class CheckLoggedInUserUseCase @Inject constructor(private val accountRepository: AccountRepository) {

  suspend operator fun invoke() = accountRepository.getUserToLocal()
}
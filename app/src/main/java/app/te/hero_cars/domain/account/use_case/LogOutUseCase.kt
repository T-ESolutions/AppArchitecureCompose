package app.te.hero_cars.domain.account.use_case

import app.te.hero_cars.domain.account.repository.AccountRepository
import app.te.network.utils.BaseResponse
import app.te.network.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LogOutUseCase @Inject constructor(private val accountRepository: AccountRepository) {

    operator fun invoke(): Flow<app.te.network.utils.Resource<app.te.network.utils.BaseResponse<*>>> = flow {
        accountRepository.clearPreferences()
        emit(accountRepository.logOut())
    }.flowOn(Dispatchers.IO)
}
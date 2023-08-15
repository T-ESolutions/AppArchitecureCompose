package app.te.hero_cars.domain.account.use_case

import app.te.hero_cars.domain.account.entity.request.SendFirebaseTokenRequest
import app.te.hero_cars.domain.account.repository.AccountRepository
import app.te.network.utils.BaseResponse
import app.te.network.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SendFirebaseTokenUseCase @Inject constructor(private val accountRepository: AccountRepository) {

  operator fun invoke(
    firebase_token: String,
    platform: String,
    device_id: String
  ): Flow<app.te.network.utils.Resource<app.te.network.utils.BaseResponse<*>>> = flow {
    emit(
      accountRepository.sendFirebaseToken(
        SendFirebaseTokenRequest(
          firebase_token,
          platform,
          device_id
        )
      )
    )
  }.flowOn(Dispatchers.IO)
}
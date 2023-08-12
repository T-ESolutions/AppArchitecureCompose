package app.te.architecture.domain.general.use_case

import app.te.architecture.domain.general.entity.UpdateFirebaseTokenRequest
import app.te.architecture.domain.general.repository.GeneralRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class UpdateFirebaseTokenUseCase @Inject constructor(
  private val generalRepository: GeneralRepository
) {
  operator fun invoke(updateFirebaseTokenRequest: UpdateFirebaseTokenRequest)= flow {
    emit(generalRepository.updateFirebaseTokenRequest(updateFirebaseTokenRequest))
  }.flowOn(Dispatchers.IO)
}

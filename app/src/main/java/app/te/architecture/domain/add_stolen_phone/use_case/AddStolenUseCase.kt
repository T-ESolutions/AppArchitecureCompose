package app.te.architecture.domain.add_stolen_phone.use_case

import app.te.architecture.domain.add_stolen_phone.entity.AddStolenPhoneRequest
import app.te.architecture.domain.add_stolen_phone.repository.AddStolenRepository
import app.te.architecture.domain.utils.BaseResponse
import app.te.architecture.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class AddStolenUseCase @Inject constructor(
  private val addStolenRepository: AddStolenRepository
) {

  operator fun invoke(request: AddStolenPhoneRequest): Flow<Resource<BaseResponse<*>>> = flow {
      emit(Resource.Loading)
      emit(addStolenRepository.addStolen(request))
  }.flowOn(Dispatchers.IO)


}
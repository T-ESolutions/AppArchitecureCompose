package app.te.hero_cars.domain.add_stolen_phone.use_case

import app.te.hero_cars.domain.add_stolen_phone.entity.AddStolenPhoneRequest
import app.te.hero_cars.domain.add_stolen_phone.repository.AddStolenRepository
import app.te.network.utils.BaseResponse
import app.te.network.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class AddStolenUseCase @Inject constructor(
  private val addStolenRepository: AddStolenRepository
) {

  operator fun invoke(request: AddStolenPhoneRequest): Flow<app.te.network.utils.Resource<app.te.network.utils.BaseResponse<*>>> = flow {
      emit(app.te.network.utils.Resource.Loading)
      emit(addStolenRepository.addStolen(request))
  }.flowOn(Dispatchers.IO)


}
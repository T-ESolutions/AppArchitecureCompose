package app.te.architecture.domain.home.use_case

import app.te.architecture.domain.home.repository.HomeRepository
import app.te.architecture.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class HomeUseCase @Inject constructor(
  private val homeRepository: HomeRepository
) {
  fun searchForStolen(serial: String) = flow {
    emit(Resource.Loading)
    emit(homeRepository.searchForPhone(serial))
  }.flowOn(Dispatchers.IO)

}

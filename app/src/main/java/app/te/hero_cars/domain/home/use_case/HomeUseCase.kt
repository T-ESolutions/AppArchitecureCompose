package app.te.hero_cars.domain.home.use_case

import app.te.hero_cars.domain.home.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class HomeUseCase @Inject constructor(
  private val homeRepository: HomeRepository
) {
  fun searchForStolen(serial: String) = flow {
    emit(app.te.network.utils.Resource.Loading)
    emit(homeRepository.searchForPhone(serial))
  }.flowOn(Dispatchers.IO)

}

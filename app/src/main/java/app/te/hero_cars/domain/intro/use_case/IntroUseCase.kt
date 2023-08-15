package app.te.hero_cars.domain.intro.use_case

import app.te.hero_cars.domain.intro.entity.AppTutorialModel
import app.te.hero_cars.domain.intro.repository.IntroRepository
import app.te.network.utils.BaseResponse
import app.te.network.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class IntroUseCase @Inject constructor(
  private val introRepository: IntroRepository
) {

  operator fun invoke(): Flow<app.te.network.utils.Resource<app.te.network.utils.BaseResponse<List<AppTutorialModel>>>> = flow {
    emit(app.te.network.utils.Resource.Loading)
    emit(introRepository.intro())
  }.flowOn(Dispatchers.IO)
}
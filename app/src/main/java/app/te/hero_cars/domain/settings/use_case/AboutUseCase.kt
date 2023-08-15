package app.te.hero_cars.domain.settings.use_case

import app.te.hero_cars.domain.settings.models.AboutData
import app.te.hero_cars.domain.settings.repository.SettingsRepository
import app.te.network.utils.BaseResponse
import app.te.network.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class AboutUseCase @Inject constructor(
  private val settingsRepository: SettingsRepository
) {

  fun aboutData(page:String): Flow<app.te.network.utils.Resource<app.te.network.utils.BaseResponse<AboutData>>> =
    flow {
      emit(app.te.network.utils.Resource.Loading)
      val result = settingsRepository.about(page)
      emit(result)
    }.flowOn(Dispatchers.IO)
}
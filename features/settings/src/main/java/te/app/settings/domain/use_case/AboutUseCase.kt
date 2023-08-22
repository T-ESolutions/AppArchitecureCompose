package te.app.settings.domain.use_case

import te.app.settings.domain.models.AboutData
import te.app.settings.domain.repository.SettingsRepository
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

  fun aboutData(page:String): Flow<Resource<BaseResponse<AboutData>>> =
    flow {
      emit(Resource.Loading)
      val result = settingsRepository.about(page)
      emit(result)
    }.flowOn(Dispatchers.IO)
}
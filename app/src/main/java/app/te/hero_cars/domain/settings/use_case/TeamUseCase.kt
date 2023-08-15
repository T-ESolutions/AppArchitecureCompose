package app.te.hero_cars.domain.settings.use_case

import app.te.hero_cars.domain.settings.models.Teams
import app.te.hero_cars.domain.settings.repository.SettingsRepository
import app.te.hero_cars.domain.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class TeamUseCase @Inject constructor(
  private val settingsRepository: SettingsRepository
) {
  operator fun invoke(): Flow<app.te.network.utils.Resource<app.te.network.utils.BaseResponse<List<Teams>>>> = flow {
    emit(app.te.network.utils.Resource.Loading)
    val result = settingsRepository.getTeam()
    emit(result)
  }.flowOn(Dispatchers.IO)

}
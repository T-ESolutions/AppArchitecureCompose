package te.app.settings.domain.use_case

import te.app.settings.domain.models.Teams
import te.app.settings.domain.repository.SettingsRepository
import app.te.network.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class TeamUseCase @Inject constructor(
  private val settingsRepository: SettingsRepository
) {
  operator fun invoke(): Flow<Resource<app.te.network.utils.BaseResponse<List<Teams>>>> = flow {
    emit(Resource.Loading)
    emit(settingsRepository.getTeam())
  }.flowOn(Dispatchers.IO)

}
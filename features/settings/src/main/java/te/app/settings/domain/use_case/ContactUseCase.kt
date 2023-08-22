package te.app.settings.domain.use_case

import te.app.settings.domain.models.ContactUs
import te.app.settings.domain.repository.SettingsRepository
import app.te.network.utils.BaseResponse
import app.te.network.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class ContactUseCase @Inject constructor(
  private val settingsRepository: SettingsRepository
) {
  operator fun invoke(): Flow<Resource<BaseResponse<List<ContactUs>>>> = flow {
    emit(Resource.Loading)
    emit(settingsRepository.getContact())
  }.flowOn(Dispatchers.IO)

}
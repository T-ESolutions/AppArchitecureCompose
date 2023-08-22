package te.app.settings.domain.repository

import te.app.settings.domain.models.AboutData
import te.app.settings.domain.models.ContactUs
import te.app.settings.domain.models.Teams
import app.te.network.utils.BaseResponse
import app.te.network.utils.Resource

interface SettingsRepository {
  suspend fun about(page: String): Resource<BaseResponse<AboutData>>
  suspend fun getTeam(): Resource<BaseResponse<List<Teams>>>
  suspend fun getContact(): Resource<BaseResponse<List<ContactUs>>>
}
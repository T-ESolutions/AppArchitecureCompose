package app.te.architecture.domain.settings.repository

import app.te.architecture.domain.settings.models.AboutData
import app.te.architecture.domain.settings.models.ContactUs
import app.te.architecture.domain.settings.models.Teams
import app.te.architecture.domain.utils.BaseResponse
import app.te.architecture.domain.utils.Resource

interface SettingsRepository {
  suspend fun about(page: String): Resource<BaseResponse<AboutData>>
  suspend fun getTeam(): Resource<BaseResponse<List<Teams>>>
  suspend fun getContact(): Resource<BaseResponse<List<ContactUs>>>
}
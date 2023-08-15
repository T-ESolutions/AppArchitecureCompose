package app.te.hero_cars.domain.settings.repository

import app.te.hero_cars.domain.settings.models.AboutData
import app.te.hero_cars.domain.settings.models.ContactUs
import app.te.hero_cars.domain.settings.models.Teams
import app.te.network.utils.BaseResponse
import app.te.network.utils.Resource

interface SettingsRepository {
  suspend fun about(page: String): app.te.network.utils.Resource<app.te.network.utils.BaseResponse<AboutData>>
  suspend fun getTeam(): app.te.network.utils.Resource<app.te.network.utils.BaseResponse<List<Teams>>>
  suspend fun getContact(): app.te.network.utils.Resource<app.te.network.utils.BaseResponse<List<ContactUs>>>
}
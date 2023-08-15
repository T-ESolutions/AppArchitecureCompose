package app.te.hero_cars.data.settings.data_source.remote

import app.te.hero_cars.domain.settings.models.AboutData
import app.te.hero_cars.domain.settings.models.ContactUs
import app.te.hero_cars.domain.settings.models.Teams
import app.te.network.utils.BaseResponse
import retrofit2.http.*

interface SettingsServices {
  @GET("V1/app/pages/{page}")
  suspend fun about(@Path("page") page: String): app.te.network.utils.BaseResponse<AboutData>

  @GET("V1/app/teams")
  suspend fun teams(): app.te.network.utils.BaseResponse<List<Teams>>

  @GET("V1/app/settings")
  suspend fun getContacts(): app.te.network.utils.BaseResponse<List<ContactUs>>


}
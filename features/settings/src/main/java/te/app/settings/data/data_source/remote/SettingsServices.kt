package te.app.settings.data.data_source.remote

import app.te.network.utils.BaseResponse
import retrofit2.http.*
import te.app.settings.domain.models.AboutData
import te.app.settings.domain.models.ContactUs
import te.app.settings.domain.models.Teams

interface SettingsServices {
  @GET("V1/app/pages/{page}")
  suspend fun about(@Path("page") page: String): BaseResponse<AboutData>

  @GET("V1/app/teams")
  suspend fun teams(): BaseResponse<List<Teams>>

  @GET("V1/app/settings")
  suspend fun getContacts(): BaseResponse<List<ContactUs>>


}
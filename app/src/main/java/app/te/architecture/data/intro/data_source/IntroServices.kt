package app.te.architecture.data.intro.data_source

import app.te.architecture.domain.intro.entity.AppTutorialModel
import app.te.architecture.domain.utils.BaseResponse
import retrofit2.http.GET

interface IntroServices {

  @GET("V1/app/screens")
  suspend fun intro(): BaseResponse<List<AppTutorialModel>>

}
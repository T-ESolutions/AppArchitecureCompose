package app.te.hero_cars.data.intro.data_source

import te.app.auth.domain.entity.model.AppTutorialModel
import app.te.network.utils.BaseResponse
import retrofit2.http.GET

interface IntroServices {

  @GET("V1/app/screens")
  suspend fun intro(): BaseResponse<List<AppTutorialModel>>

}
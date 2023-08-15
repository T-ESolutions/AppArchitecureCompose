package app.te.hero_cars.data.intro.data_source

import app.te.hero_cars.domain.intro.entity.AppTutorialModel
import app.te.network.utils.BaseResponse
import retrofit2.http.GET

interface IntroServices {

  @GET("V1/app/screens")
  suspend fun intro(): app.te.network.utils.BaseResponse<List<AppTutorialModel>>

}
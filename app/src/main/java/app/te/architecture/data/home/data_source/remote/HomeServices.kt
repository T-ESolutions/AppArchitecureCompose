package app.te.architecture.data.home.data_source.remote

import app.te.architecture.domain.home.models.HomeData
import app.te.architecture.domain.utils.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeServices {
  @GET("v1/app/home/categories/{cat_id}")
  suspend fun homeStudent(@Path("cat_id") cat_id: Int): BaseResponse<HomeData>

}
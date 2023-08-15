package app.te.hero_cars.data.home.data_source.remote

import app.te.hero_cars.data.home.dto.SearchData
import app.te.network.utils.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeServices {
  @GET("V1/user/search-stolen-phones")
  suspend fun searchForPhone(@Query("serial") serial: String): app.te.network.utils.BaseResponse<SearchData>

}
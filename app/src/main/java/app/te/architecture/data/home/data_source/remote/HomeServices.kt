package app.te.architecture.data.home.data_source.remote

import app.te.architecture.data.home.dto.SearchData
import app.te.architecture.domain.utils.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeServices {
  @GET("V1/user/search-stolen-phones")
  suspend fun searchForPhone(@Query("serial") serial: String): BaseResponse<SearchData>

}
package app.te.architecture.domain.home.repository
import app.te.architecture.domain.home.models.HomeData
import app.te.architecture.domain.utils.BaseResponse
import app.te.architecture.domain.utils.Resource

interface HomeRepository {
  suspend fun getHome(cat_id: Int): Resource<BaseResponse<HomeData>>
}
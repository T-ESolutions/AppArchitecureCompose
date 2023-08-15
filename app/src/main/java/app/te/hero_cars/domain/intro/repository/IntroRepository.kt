package app.te.hero_cars.domain.intro.repository

import te.app.auth.domain.entity.model.AppTutorialModel
import app.te.network.utils.BaseResponse
import app.te.network.utils.Resource

interface IntroRepository {
  suspend fun intro(): Resource<BaseResponse<List<AppTutorialModel>>>
}
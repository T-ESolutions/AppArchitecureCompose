package app.te.hero_cars.domain.intro.repository

import app.te.hero_cars.domain.intro.entity.AppTutorialModel
import app.te.network.utils.BaseResponse
import app.te.network.utils.Resource

interface IntroRepository {
  suspend fun intro(): app.te.network.utils.Resource<app.te.network.utils.BaseResponse<List<AppTutorialModel>>>
}
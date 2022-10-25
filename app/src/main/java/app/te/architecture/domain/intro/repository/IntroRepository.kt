package app.te.architecture.domain.intro.repository

import app.te.architecture.domain.intro.entity.AppTutorialModel
import app.te.architecture.domain.utils.BaseResponse
import app.te.architecture.domain.utils.Resource

interface IntroRepository {
  suspend fun intro(): Resource<BaseResponse<List<AppTutorialModel>>>
}
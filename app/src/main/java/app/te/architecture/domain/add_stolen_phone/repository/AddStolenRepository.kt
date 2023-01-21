package app.te.architecture.domain.add_stolen_phone.repository

import app.te.architecture.domain.add_stolen_phone.entity.AddStolenPhoneRequest
import app.te.architecture.domain.utils.BaseResponse
import app.te.architecture.domain.utils.Resource

interface AddStolenRepository {
    suspend fun addStolen(request: AddStolenPhoneRequest): Resource<BaseResponse<*>>
}
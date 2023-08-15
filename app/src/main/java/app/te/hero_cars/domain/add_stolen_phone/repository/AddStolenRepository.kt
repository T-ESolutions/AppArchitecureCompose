package app.te.hero_cars.domain.add_stolen_phone.repository

import app.te.hero_cars.domain.add_stolen_phone.entity.AddStolenPhoneRequest
import app.te.network.utils.BaseResponse
import app.te.network.utils.Resource

interface AddStolenRepository {
    suspend fun addStolen(request: AddStolenPhoneRequest): app.te.network.utils.Resource<app.te.network.utils.BaseResponse<*>>
}
package app.te.architecture.presentation.home.ui_state

import app.te.architecture.domain.home.models.SerialSearchDM

data class StolenUiItemState(val searchData: SerialSearchDM) {

    fun governVisibility(): Boolean =
        searchData.gov_name.isNotEmpty()

    fun cityVisibility(): Boolean =
        searchData.city_name.isNotEmpty()

    fun callVisibility(): Boolean =
        (searchData.added_by != null && searchData.added_by.phone.isNotEmpty()) || searchData.phone != null

    fun getPhoneNumber(): String {
        if (searchData.added_by != null && searchData.added_by.phone.isNotEmpty())
            return searchData.added_by.phone
        if (searchData.phone != null)
            return searchData.phone
        return ""
    }
}
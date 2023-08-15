package app.te.hero_cars.presentation.contact_us

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.te.hero_cars.domain.settings.models.ContactUs
import app.te.hero_cars.domain.settings.use_case.ContactUseCase
import app.te.network.utils.Resource
import app.te.hero_cars.presentation.contact_us.ui_state.ContactUsState
import app.te.hero_cars.presentation.contact_us.ui_state.ContactUsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactUsViewModel @Inject constructor(
    private val contactUseCase: ContactUseCase,
) : ViewModel() {
    private val _contactState =
        MutableStateFlow(ContactUsState())
    val contactState = _contactState

    fun getContactUs() {
        viewModelScope.launch {
            contactUseCase.invoke().collect { resource ->
                when (resource) {
                    is app.te.network.utils.Resource.Success -> {
                        _contactState.value = ContactUsState(
                            data = mapToContactUiState(resource.value.data)
                        )
                    }
                    is app.te.network.utils.Resource.Failure -> {
                        _contactState.value = ContactUsState(
                            failureStatus = resource
                        )
                    }
                    is app.te.network.utils.Resource.Loading -> {
                        _contactState.value = ContactUsState(
                            isLoading = true
                        )
                    }
                    else -> {}
                }

            }
        }
    }

    private fun mapToContactUiState(
        data: List<ContactUs>
    ): List<ContactUsUiState> {
        val contactData: MutableList<ContactUsUiState> = mutableListOf()
        data.forEach { item ->
            if (item.key == "whatsapp" || item.key == "facebook" || item.key == "youtube")
                contactData.add(ContactUsUiState(link = item.value?:"", image = item.image, item.id))
        }
        return contactData
    }
}
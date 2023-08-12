package app.te.architecture.presentation.add_stolen_phone.view_model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.te.architecture.R
import app.te.architecture.data.general.data_source.dto.countries.CityModel
import app.te.architecture.data.general.data_source.dto.countries.Government
import app.te.architecture.data.home.dto.Brand
import app.te.architecture.data.home.dto.Model
import app.te.architecture.domain.add_stolen_phone.entity.AddStolenPhoneRequest
import app.te.architecture.domain.add_stolen_phone.use_case.AddStolenUseCase
import app.te.architecture.domain.utils.Resource
import app.te.architecture.presentation.add_stolen_phone.events.AddStolenFormEvent
import app.te.architecture.presentation.add_stolen_phone.states.AddStolenFormState
import app.te.architecture.presentation.add_stolen_phone.states.AddStolenResultState
import app.te.architecture.presentation.base.utils.Constants
import app.te.architecture.presentation.base.validation_usecase.ValidateCheckBox
import app.te.architecture.presentation.base.validation_usecase.ValidateField
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddStolenPhoneViewModel @Inject constructor(
    private val addStolenUseCase: AddStolenUseCase,
    private val validateField: ValidateField,
    private val validateCheckBox: ValidateCheckBox
) : ViewModel() {

    var state by mutableStateOf(AddStolenFormState())

    private val _resultState =
        MutableStateFlow(AddStolenResultState())
    val resultState = _resultState.asStateFlow()


    fun onEvent(event: AddStolenFormEvent) {
        when (event) {
            is AddStolenFormEvent.GovernChanged -> {
                state = state.copy(
                    govern = event.govern,
                    governError = validateField.invoke(event.govern).errorMessage
                )
            }
            is AddStolenFormEvent.CityChanged -> {
                state = state.copy(
                    city = event.city,
                    cityError = validateField.invoke(event.city).errorMessage
                )
            }
            is AddStolenFormEvent.BrandChanged -> {
                state = state.copy(
                    brand = event.brand,
                    brandError = validateField.invoke(event.brand).errorMessage
                )
            }
            is AddStolenFormEvent.ModelChanged -> {
                state = state.copy(
                    model = event.model,
                    modelError = validateField.invoke(event.model).errorMessage
                )
            }
            is AddStolenFormEvent.PhoneChanged -> {
                state = state.copy(
                    phone = event.phone,
                    phoneError = validateField.invoke(event.phone).errorMessage
                )
            }
            is AddStolenFormEvent.SerialChanged -> {
                state = state.copy(
                    serial = event.serial,
                    serialError = validateField.invoke(event.serial).errorMessage
                )
            }
            is AddStolenFormEvent.ImageChanged -> {
                state = state.copy(
                    image = event.image
                )
            }
            is AddStolenFormEvent.NotesChanged -> {
                state = state.copy(
                    notes = event.notes,
                    notesError = validateField.invoke(event.notes).errorMessage
                )
            }
            is AddStolenFormEvent.CheckBoxChanged -> {
                state = state.copy(
                    checkBoxChecked = event.checkBoxChecked,
                    checkBoxCheckedError = R.string.please_accept_terms
                )
            }
            is AddStolenFormEvent.Submit -> {
                submitData()
            }

        }
    }

    private fun submitData() {
        if (validateInputs()) {
            Log.e("submitData", "submitData: ")
            val request = AddStolenPhoneRequest(
                brandId = state.brandId,
                modelId = state.modelId,
                govId = state.governId,
                cityId = state.cityId,
                serial = state.serial,
                phone = state.phone,
                notes = state.notes
            )
            request.setImage(state.image, Constants.IMAGE)
            viewModelScope.launch {
                addStolenUseCase.invoke(
                    request
                )
                    .collect { resource ->
                        when (resource) {
                            is Resource.Success -> {
                                _resultState.value = AddStolenResultState(
                                    data = resource.value.data,
                                    msg = resource.value.message
                                )
                            }
                            is Resource.Failure -> {
                                _resultState.value = AddStolenResultState(
                                    failureStatus = resource
                                )
                            }
                            is Resource.Loading -> {
                                _resultState.value = AddStolenResultState(
                                    isLoading = true
                                )
                            }
                            else -> {}
                        }
                    }
            }
        }
    }

    private fun validateInputs(): Boolean {
        val governResult = validateField.invoke(state.govern)
        val cityResult = validateField.invoke(state.city)
        val brandResult = validateField.invoke(state.brand)
        val modelResult = validateField.invoke(state.model)
        val phoneResult = validateField.invoke(state.phone)
        val serialResult = validateField.invoke(state.serial)
        val notesResult = validateField.invoke(state.notes)
        val termsResult = validateCheckBox.invoke(state.checkBoxChecked)
        Log.e("validateInputs", "validateInputs: " + termsResult.errorMessage)
        val hasError = listOf(
            governResult,
            cityResult,
            brandResult,
            phoneResult,
            modelResult,
            serialResult,
            notesResult,
            termsResult
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                governError = governResult.errorMessage,
                cityError = cityResult.errorMessage,
                brandError = brandResult.errorMessage,
                modelError = modelResult.errorMessage,
                phoneError = phoneResult.errorMessage,
                serialError = serialResult.errorMessage,
                notesError = notesResult.errorMessage,
                checkBoxCheckedError = R.string.please_accept_terms
            )
            return false
        }
        return true
    }

    fun updateGovern(government: Government) {
        state = state.copy(
            govern = government.title,
            governId = government.id.toString(),
            governError = null
        )
    }

    fun updateCity(city: CityModel) {
        state = state.copy(city = city.name, cityId = city.id.toString(), cityError = null)
    }

    fun updateBrand(brand: Brand) {
        state = state.copy(brand = brand.title, brandId = brand.id.toString(), brandError = null)
    }

    fun updateModel(model: Model) {
        state = state.copy(model = model.title, modelId = model.id.toString(), modelError = null)
    }

    fun updateSelectedImage(path: String, text: Int) {
        state = state.copy(image = path, imageText = text)
    }

}
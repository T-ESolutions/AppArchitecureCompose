package app.te.architecture.presentation.auth.sign_up

import android.content.Context
import android.view.View
import androidx.databinding.Bindable
import app.te.architecture.BR
import app.te.architecture.R
import app.te.architecture.domain.auth.entity.request.RegisterRequest
import app.te.architecture.domain.utils.isValidEgyNumber
import app.te.architecture.domain.utils.isValidEmail
import app.te.architecture.presentation.base.BaseUiState
import app.te.architecture.presentation.base.utils.Constants

class RegisterUiState(val context: Context) : BaseUiState() {
    @Bindable
    val request = RegisterRequest()

    @Bindable
    var emailVisibility: Int = View.GONE
        set(value) {
            notifyPropertyChanged(BR.emailVisibility)
            field = value
        }

    @Bindable
    var cityName: String = ""
        set(value) {
            notifyPropertyChanged(BR.cityName)
            field = value
        }

    /**
    check phone validation if
    - when cityId empty and user try writing phone warn him to select city first
    - when city == egy code and not match phone regex show error
    - when city != egy code error will hide
     */
    fun onPhoneChange(s: CharSequence, start: Int, before: Int, count: Int) {
        if (request.city_id.isEmpty())
            request.validation.phoneError.set(context.getString(R.string.choose_country))
        else {
            if (request.city_id == Constants.EGYPT_ID && !s.toString().isValidEgyNumber())
                request.validation.phoneError.set(context.getString(R.string.valid_egy_number))
            else
                request.validation.phoneError.set(null)
        }
    }

    /**
    check email validation if
    - email match regex no error show
    - email not match regex error of invalid email will show
     */
    fun onMailChange(s: CharSequence, start: Int, before: Int, count: Int) {
        if (s.isNotEmpty() && !s.toString().isValidEmail())
            request.validation.emailError.set(context.getString(R.string.email_not_valid))
        else
            request.validation.emailError.set(null)
    }

    /**
    check when email validation when
    - cityId not empty and city not egypt
     */
    fun checkValidation(): Boolean {
        var isValid = true
        if (request.name.isEmpty()) {
            request.validation.nameError.set(context.getString(R.string.empty_warning))
            isValid = false
        }
        if (request.phone.isEmpty()) {
            request.validation.phoneError.set(context.getString(R.string.empty_warning))
            isValid = false
        }

        if (request.city_id.isEmpty()) {
            request.validation.cityError.set(context.getString(R.string.empty_warning))
            isValid = false
        }
        if (request.city_id.isNotEmpty() && request.city_id != Constants.EGYPT_ID && request.email.isEmpty()) {
            request.validation.emailError.set(context.getString(R.string.empty_warning))
            isValid = false
        } else
            request.validation.emailError.set(null)

        if (request.password.isEmpty()) {
            request.validation.passwordError.set(context.getString(R.string.empty_warning))
            isValid = false
        }

        return isValid
    }

    // this for writing phone matches with country selected
    fun updatePhone() {
        if (request.phone.isNotEmpty())
            request.phone = ""
        notifyPropertyChanged(BR.request)
    }


}
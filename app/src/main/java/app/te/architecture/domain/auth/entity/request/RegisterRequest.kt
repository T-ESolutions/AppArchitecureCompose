package app.te.architecture.domain.auth.entity.request

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.databinding.ObservableField
import app.te.architecture.domain.utils.BaseRequest
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
class RegisterRequest : BaseRequest(), Parcelable {

    var name: String = ""
        set(value) {
            validation.nameError.set(null)
            field = value
        }
    var phone: String = ""
    var city_id: String = ""
        set(value) {
            validation.cityError.set(null)
            field = value
        }
    var password: String = ""
        set(value) {
            validation.passwordError.set(null)
            field = value
        }
    var email: String = ""

    var otp: String = ""


    @Transient
    var validation: RegisterValidationException = RegisterValidationException()

}

@Keep
class RegisterValidationException {
    var nameError: ObservableField<String> = ObservableField<String>()
    var phoneError: ObservableField<String> = ObservableField<String>()
    var passwordError: ObservableField<String> = ObservableField<String>()
    var cityError: ObservableField<String> = ObservableField<String>()
    var emailError: ObservableField<String> = ObservableField<String>()


}

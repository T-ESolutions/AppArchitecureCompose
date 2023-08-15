package app.te.core.validation_usecase

import android.content.Context
import app.te.core.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ValidatePhone @Inject constructor(@ApplicationContext val context: Context) {
    operator fun invoke(phone: String): ValidationResult {
        if (phone.isEmpty())
            return ValidationResult(
                successful = false,
                errorMessage = context.getString(R.string.empty_warning)
            )

        return ValidationResult(
            successful = true
        )
    }
}
package app.te.core.validation_usecase

import android.content.Context
import android.util.Patterns
import app.te.core.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ValidateEmail @Inject constructor(@ApplicationContext val context: Context) {
    operator fun invoke(email: String): ValidationResult {
        if (email.isBlank())
            return ValidationResult(
                successful = false,
                errorMessage = context.getString(R.string.empty_warning)
            )
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = context.getString(R.string.email_not_valid)
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}
package app.te.core.validation_usecase

import android.content.Context
import app.te.core.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ValidatePassword @Inject constructor(@ApplicationContext val context: Context) {
    operator fun invoke(password: String): ValidationResult {
        if (password.length < 6)
            return ValidationResult(
                successful = false,
                errorMessage = context.getString(R.string.password_length_warning)
            )

        return ValidationResult(
            successful = true
        )
    }
}
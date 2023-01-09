package app.te.architecture.presentation.base.validation_usecase

import android.content.Context
import android.util.Patterns
import app.te.architecture.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ValidatePassword @Inject constructor(@ApplicationContext val context: Context) {
    operator fun invoke(password: String): ValidationResult {
        if (password.length < 8)
            return ValidationResult(
                successful = false,
                errorMessage = context.getString(R.string.password_length_warning)
            )

        return ValidationResult(
            successful = true
        )
    }
}
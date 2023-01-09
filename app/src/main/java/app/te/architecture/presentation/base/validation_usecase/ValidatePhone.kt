package app.te.architecture.presentation.base.validation_usecase

import android.content.Context
import android.util.Patterns
import app.te.architecture.R
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
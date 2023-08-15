package app.te.core.validation_usecase

import android.content.Context
import app.te.core.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ValidateField @Inject constructor(@ApplicationContext val context: Context) {
    operator fun invoke(filed: String): ValidationResult {
        if (filed.isEmpty())
            return ValidationResult(
                successful = false,
                errorMessage = context.getString(R.string.empty_warning)
            )

        return ValidationResult(
            successful = true
        )
    }
}
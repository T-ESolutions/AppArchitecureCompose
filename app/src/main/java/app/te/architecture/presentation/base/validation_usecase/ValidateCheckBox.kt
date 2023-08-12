package app.te.architecture.presentation.base.validation_usecase

import android.content.Context
import android.util.Patterns
import app.te.architecture.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ValidateCheckBox @Inject constructor(@ApplicationContext val context: Context) {
    operator fun invoke(filed: Boolean): ValidationResult {
        if (!filed)
            return ValidationResult(
                successful = false,
                errorMessage = context.getString(R.string.empty_warning)
            )

        return ValidationResult(
            successful = true
        )
    }
}
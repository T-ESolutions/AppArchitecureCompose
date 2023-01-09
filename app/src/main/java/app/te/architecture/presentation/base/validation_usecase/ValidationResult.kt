package app.te.architecture.presentation.base.validation_usecase

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
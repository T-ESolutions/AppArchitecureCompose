package app.te.core.validation_usecase

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
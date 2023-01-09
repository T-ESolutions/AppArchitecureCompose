package app.te.architecture.presentation.auth.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import app.te.architecture.R

val fonts = FontFamily(
    Font(R.font.cairo_bold, weight = FontWeight.Bold),
    Font(R.font.cairo_medium, weight = FontWeight.Medium),
    Font(R.font.cairo_regular, weight = FontWeight.Normal)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyMedium = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Medium
    ),

    bodyLarge = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold
    ),

    bodySmall = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal
    ),

    displayLarge = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold
    ),
    displayMedium = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Medium
    ),

    displaySmall = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal
    ),

    headlineLarge = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold
    ),

    headlineMedium = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Medium
    ),

    headlineSmall = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal
    ),
    titleLarge = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold
    ),
    titleMedium = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Medium
    ),
    titleSmall = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal
    ),
    labelLarge = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold
    ),
    labelMedium = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Medium
    ),
    labelSmall = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal
    ),
)

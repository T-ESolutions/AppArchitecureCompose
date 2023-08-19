package app.te.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColorScheme(
    primary = colorPrimary,
    secondary = secondaryLight,
    secondaryContainer = secondaryContainer,
    primaryContainer = primaryContainer,
    onPrimaryContainer = onPrimaryContainer,
    outline = outline,
    outlineVariant = outlineVariant,
    onPrimary = onPrimary,
    background = background,
    onBackground = onBackground,
    inversePrimary = inversePrimary,
    tertiary = tertiary,
)

private val LightColorPalette = lightColorScheme(
    primary = colorPrimary,
    secondary = secondaryLight,
    secondaryContainer = secondaryContainer,
    primaryContainer = primaryContainer,
    onPrimaryContainer = onPrimaryContainer,
    outline = outline,
    outlineVariant = outlineVariant,
    onPrimary = onPrimary,
    background = background,
    onBackground = onBackground,
    inversePrimary = inversePrimary,
    tertiary = tertiary,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun AppArchitectureTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
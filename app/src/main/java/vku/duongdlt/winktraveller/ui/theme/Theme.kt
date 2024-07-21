package vku.duongdlt.winktraveller.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Stable
data class DarkTheme(val isDark: Boolean = false)

val LocalTheme = compositionLocalOf { DarkTheme() }

@Composable
fun HeliaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val theme = DarkTheme(darkTheme)
    CompositionLocalProvider(
        LocalHeliaTypography provides heliaTypography,
        LocalHeliaShapes provides heliaShapes,
        LocalHeliaColorSchema provides heliaColorSchema,
        LocalTheme provides theme
    ) {
        MaterialTheme(content = content)
    }
}

object HeliaTheme {
    val typography: HeliaTypography
        @Composable
        get() = LocalHeliaTypography.current
    val shapes: HeliaShapes
        @Composable
        get() = LocalHeliaShapes.current
    val colors: HeliaColorSchema
        @Composable
        get() = LocalHeliaColorSchema.current
    val theme: DarkTheme
        @Composable
        get() = LocalTheme.current

    val primaryTextColor
        @Composable get() = if (theme.isDark) colors.white else colors.greyscale900

    val secondaryTextColor
        @Composable get() = if (theme.isDark) colors.greyscale100 else colors.greyscale800

    val backgroundColor
        @Composable get() = if (theme.isDark) colors.dark1 else colors.white
}


object LoginSocialButtonRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = HeliaTheme.colors.primary200

    @Composable
    override fun rippleAlpha(): RippleAlpha {
        return RippleTheme.defaultRippleAlpha(Color.Black, lightTheme = !isSystemInDarkTheme())
    }

}



@Composable
fun WinKTravellerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
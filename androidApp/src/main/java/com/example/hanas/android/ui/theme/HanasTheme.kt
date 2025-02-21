package com.example.hanas.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalColorScheme = staticCompositionLocalOf<ColorScheme> { LightColorScheme }
val LocalTypography = staticCompositionLocalOf { Typography(Color.Unspecified) }

object HanasTheme {
    val colorScheme: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}

@Composable
fun HanasTheme(content: @Composable () -> Unit) {
    val isDarkTheme = isSystemInDarkTheme()
    val colorScheme = if (isDarkTheme) DarkColorScheme else LightColorScheme
    val typography = Typography(colorScheme.text)

    CompositionLocalProvider(
        LocalColorScheme provides colorScheme,
        LocalTypography provides typography,
        content = content,
    )
}

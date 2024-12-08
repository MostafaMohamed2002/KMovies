package org.mostafadevo.kmovies.presentation.theme

// Generated using MaterialKolor Builder version 1.0.1 (101)
// https://materialkolor.com/?color_seed=FFFFC107&dark_mode=true


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.materialkolor.DynamicMaterialTheme
import com.materialkolor.PaletteStyle
import com.materialkolor.rememberDynamicMaterialThemeState

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    amoled: Boolean = false,
    content: @Composable () -> Unit,
) {
    val dynamicThemeState = rememberDynamicMaterialThemeState(
        isDark = darkTheme,
        isAmoled = amoled,
        style = PaletteStyle.TonalSpot,
        seedColor = SeedColor,
    )

    DynamicMaterialTheme(
        state = dynamicThemeState,
        animate = true,
        content = content,
    )
}
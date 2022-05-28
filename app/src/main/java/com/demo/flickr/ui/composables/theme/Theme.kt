package com.demo.flickr.ui.composables.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = PRIMARY,
    primaryVariant = PRIMARY_DARK,
    secondary = PRIMARY_LIGHT,
    surface = Color.White,
    onSecondary = PRIMARY_LIGHT,
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = LightColorPalette,
        content = content
    )
}
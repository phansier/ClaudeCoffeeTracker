package com.example.coffeetracker.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun CoffeeTrackerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> darkColorScheme(
            primary = Color(0xFF9B6F52),
            secondary = Color(0xFF775B45),
            tertiary = Color(0xFF634B38),
            surface = Color(0xFF1C1B1F)
        )
        else -> lightColorScheme(
            primary = Color(0xFF6F4E37),
            secondary = Color(0xFF936639),
            tertiary = Color(0xFFB87A3D),
            surface = Color(0xFFFFFBFE)
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
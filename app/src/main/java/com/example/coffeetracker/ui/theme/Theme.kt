package com.example.coffeetracker.ui.theme

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
        typography = Typography(),
        content = content
    )
}
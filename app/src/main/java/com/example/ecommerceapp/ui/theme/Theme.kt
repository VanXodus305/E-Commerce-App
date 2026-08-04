package com.example.ecommerceapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
	primary = Olive80,
	secondary = Sand80,
	tertiary = Coral80,
	background = Color(0xFF0F1115),
	surface = Color(0xFF151A22),
	surfaceVariant = Color(0xFF202837),
	onPrimary = Color(0xFF04211E),
	onSecondary = Color(0xFF2A1800),
	onTertiary = Color(0xFF361106)
)

private val LightColorScheme = lightColorScheme(
	primary = Olive40,
	secondary = Sand40,
	tertiary = Coral40,
	background = Color(0xFFF7F3EC),
	surface = Color(0xFFFFFBF6),
	surfaceVariant = Color(0xFFE8E2D8),
	onPrimary = Color.White,
	onSecondary = Color.White,
	onTertiary = Color.White
)

@Composable
fun ECommerceAppTheme(
	darkTheme: Boolean = isSystemInDarkTheme(),
	dynamicColor: Boolean = false,
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

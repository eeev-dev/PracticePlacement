package com.example.practiceplacement.ui.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


@Composable
fun SetStatusBarColor(color: Color) {
    val view = LocalView.current
    val window = (view.context as? Activity)?.window ?: return

    window.statusBarColor = android.graphics.Color.BLACK

    val insetsController = WindowCompat.getInsetsController(window, view)
    insetsController.isAppearanceLightStatusBars = false
}
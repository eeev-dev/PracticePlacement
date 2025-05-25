package com.example.practiceplacement.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.practiceplacement.ui.AboutScreen
import com.example.practiceplacement.ui.LetterScreen
import com.example.practiceplacement.ui.LoginScreen
import com.example.practiceplacement.ui.PracticeScreen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = "practice_screen") {
        composable("login_screen") { LoginScreen(navController) }
        composable("letter_screen") { LetterScreen(navController) }
        composable("practice_screen") { PracticeScreen(navController, ) }
        composable(
            "about_screen/{placeId}",
            arguments = listOf(navArgument("placeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val placeId = backStackEntry.arguments?.getInt("placeId") ?: 0
            AboutScreen(navController, placeId)
        }
    }
}
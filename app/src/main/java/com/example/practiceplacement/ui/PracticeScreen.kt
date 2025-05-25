package com.example.practiceplacement.ui

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.practiceplacement.ui.navigation.Appbar
import com.example.practiceplacement.utils.isDatePassed
import com.example.practiceplacement.utils.parseDeadlineToLocalDate
import com.example.practiceplacement.viewmodels.PracticeViewModel
import java.time.LocalDate
import java.time.LocalDateTime


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PracticeScreen(
    navController: NavController,
    viewModel: PracticeViewModel = viewModel()
) {
    val context = LocalContext.current
    val id = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE).getInt("intern_id", -1)

    val internInfo = viewModel.internInfo
    val message = viewModel.message
    val errorMessage = viewModel.errorMessage

    LaunchedEffect(id) {
        viewModel.loadIntern(id)
    }

    val isLogin = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE).getBoolean("isLogin", false)
    if (!isLogin)  {
        LoginScreen(navController)
    }
    else {
        Box(Modifier.fillMaxSize()) {
            if (internInfo == null) {
                Box(Modifier.align(Alignment.Center)) { CircularProgressIndicator() }
            } else {
                val deadlineString = internInfo.deadline
                val deadline = parseDeadlineToLocalDate(deadlineString.toString())
                val status = internInfo.status
                Appbar(navController, "Практика") {
                    if (isDatePassed(deadline) || status == "Ожидает подтверждения" || status == "Подтвержден")
                        ProcessScreen(navController, status)
                    else SelectionScreen(navController, deadline)
                }
            }
        }
    }
}
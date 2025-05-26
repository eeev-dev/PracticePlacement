package com.example.practiceplacement.ui

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.practiceplacement.ui.navigation.Appbar
import com.example.practiceplacement.utils.isDatePassed
import com.example.practiceplacement.utils.parseDeadlineToLocalDate
import com.example.practiceplacement.viewmodels.PracticeViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.time.LocalDate
import java.time.LocalDateTime
import androidx.compose.runtime.getValue


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PracticeScreen(
    navController: NavController,
    viewModel: PracticeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val id = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE).getInt("intern_id", -1)

    val internInfo = viewModel.internInfo
    val message = viewModel.message
    val errorMessage = viewModel.errorMessage

    LaunchedEffect(id) {
        viewModel.loadIntern(id)
    }

    val isRefreshing by viewModel.isRefreshing

    val isLogin = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        .getBoolean("isLogin", false)

    Appbar(navController, "Практика", onExit = { viewModel.clearData() }) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = { viewModel.refresh(context) }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    if (!isLogin) {
                        navController.navigate("login_screen")
                    } else {
                        if (internInfo == null) {
                            Box(Modifier.fillParentMaxSize(), contentAlignment = Alignment.Center) {
                                CircularProgressIndicator()
                            }
                        } else {
                            val deadlineString = internInfo.deadline
                            val deadline = parseDeadlineToLocalDate(deadlineString.toString())
                            val status = internInfo.status
                            Box(Modifier.fillParentMaxSize(), contentAlignment = Alignment.Center) {
                                if (isDatePassed(deadline) || status == "Ожидает подтверждения" || status == "Подтвержден") navController.navigate("process_screen")
                                else navController.navigate("selection_screen")
                            }
                        }
                    }
                }
            }
        }
    }

}
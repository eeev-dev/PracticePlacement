package com.example.practiceplacement.ui

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.practiceplacement.data.remote.models.Place
import com.example.practiceplacement.ui.components.BlueRectangularButton
import com.example.practiceplacement.ui.components.Deadline
import com.example.practiceplacement.ui.components.Message
import com.example.practiceplacement.ui.tabs.company.CompanyItem
import com.example.practiceplacement.viewmodels.LoginViewModel
import com.example.practiceplacement.viewmodels.SelectionViewModel
import java.time.LocalDate
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.practiceplacement.ui.navigation.Appbar
import com.example.practiceplacement.utils.formatDateRussian
import com.example.practiceplacement.utils.parseDeadlineToLocalDate
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SelectionScreen(
    navController: NavController,
    viewModel: SelectionViewModel = hiltViewModel()
) {
    val places by viewModel.places.collectAsState()
    val message = ""

    val context = LocalContext.current
    val id = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE).getInt("intern_id", -1)

    LaunchedEffect(id) {
        viewModel.loadIntern(id)
    }

    val internInfo = viewModel.internInfo

    val isRefreshing by viewModel.isRefreshing

    Appbar(navController, "Практика", onExit = { viewModel.clearData() }) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = { viewModel.refresh(context) }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    if (internInfo == null) {
                        Box(Modifier.fillParentMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    } else {
                        Box(
                            Modifier
                                .fillParentMaxSize()
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Column {
                                val deadlineString = internInfo.deadline
                                val deadline = parseDeadlineToLocalDate(deadlineString.toString())
                                Message(message)
                                if (internInfo.deadline != null) Deadline(formatDateRussian(deadline))
                                LazyColumn {
                                    places.forEach { place ->
                                        item {
                                            CompanyItem(place) { navController.navigate("about_screen/${place.id}") }
                                        }
                                    }
                                    item {
                                        Spacer(Modifier.height(50.dp))
                                    }
                                }
                            }
                            Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                                BlueRectangularButton("Свое место практики") {
                                    navController.navigate(
                                        "letter_screen"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

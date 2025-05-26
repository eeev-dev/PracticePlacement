package com.example.practiceplacement.ui

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.practiceplacement.R
import com.example.practiceplacement.ui.components.BlueRectangularButton
import com.example.practiceplacement.ui.components.ClipboardTextField
import com.example.practiceplacement.ui.components.SendConfirmation
import com.example.practiceplacement.ui.components.Subject
import com.example.practiceplacement.ui.components.Supervisor
import com.example.practiceplacement.ui.navigation.Appbar
import com.example.practiceplacement.ui.tabs.review.RatingBar
import com.example.practiceplacement.utils.copyToDownloads
import com.example.practiceplacement.utils.openFile
import com.example.practiceplacement.viewmodels.ProcessViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProcessScreen(
    navController: NavController,
    viewModel: ProcessViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val id = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE).getInt("intern_id", -1)

    val internInfo = viewModel.internInfo

    LaunchedEffect(id) {
        viewModel.loadIntern(id, context)
    }

    val isRefreshing by viewModel.isRefreshing

    var dialogState by remember { mutableStateOf(false) }
    var rating by remember { mutableIntStateOf(0) }
    val headTeacher = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        .getString("head_teacher", "")

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
                        if (internInfo.status == "Без заявки") navController.navigate("practice_screen") {
                            popUpTo(
                                "process_screen"
                            ) { inclusive = true }
                        }
                        Box(Modifier.fillParentMaxSize(), contentAlignment = Alignment.Center) {
                            if (dialogState) {
                                SendConfirmation(
                                    onClose = { dialogState = false },
                                    content = {
                                    Column {
                                        var text by remember { mutableStateOf("") }
                                        Column(modifier = Modifier.weight(1f, fill = false)) {
                                            RatingBar(
                                                rating,
                                                onRatingChanged = { newRating ->
                                                    rating = newRating
                                                }
                                            )

                                            ClipboardTextField(
                                                value = text,
                                                label = "Вставьте отзыв",
                                                onValueChange = { text = it }
                                            )

                                            Spacer(Modifier.height(12.dp))
                                        }

                                        BlueRectangularButton("Оставить отзыв") {
                                            if (rating == 0 || text.isEmpty()) Toast.makeText(context, "Нужно заполнить все", Toast.LENGTH_SHORT).show()
                                            else {
                                                internInfo.place_id?.let { viewModel.sendReview(rating, text, LocalDate.now().toString(), internInfo.place_id) }
                                                dialogState = false
                                            }
                                        }
                                    }
                                })
                            }
                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .background(Color.White)
                                    .padding(12.dp)
                            ) {
                                Column {
                                    Supervisor(
                                        "Руководитель практики",
                                        headTeacher.toString(),
                                        R.drawable.teacher
                                    )
                                    Spacer(Modifier.height(12.dp))
                                    Subject(
                                        "Предприятие",
                                        internInfo.place.toString(),
                                        R.drawable.company,
                                        internInfo.status
                                    )
                                }
                                if (internInfo.status == "Подтвержден") {
                                    Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                                        Column {
                                            BlueRectangularButton("Оставить отзыв") {
                                                dialogState = true
                                            }
                                            Spacer(Modifier.height(12.dp))
                                            BlueRectangularButton("Шаблон недельного отчета") {
                                                openFile(
                                                    context,
                                                    copyToDownloads(context, "Шаблон отчета.docx")
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
        }
    }
}
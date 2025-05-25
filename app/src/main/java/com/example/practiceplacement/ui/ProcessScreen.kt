package com.example.practiceplacement.ui

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import com.example.practiceplacement.R
import com.example.practiceplacement.ui.components.BlueRectangularButton
import com.example.practiceplacement.ui.components.ClipboardTextField
import com.example.practiceplacement.ui.components.SendConfirmation
import com.example.practiceplacement.ui.components.Subject
import com.example.practiceplacement.ui.components.Supervisor
import com.example.practiceplacement.ui.tabs.review.RatingBar
import com.example.practiceplacement.utils.copyToDownloads
import com.example.practiceplacement.utils.openFile

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProcessScreen(
    navController: NavController,
    status: String?
) {
    val context = LocalContext.current
    var dialogState by remember { mutableStateOf(false) }
    var rating by remember { mutableIntStateOf(0) }
    val headTeacher = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE).getString("head_teacher", "")
    if (dialogState) {
        SendConfirmation({ dialogState = false }, "Оставить отзыв") {
            Column {
                RatingBar(
                    rating,
                    onRatingChanged = { newRating ->
                        rating = newRating
                    }
                )
                var text by remember { mutableStateOf("") }

                ClipboardTextField(
                    value = text,
                    label = "Вставьте отзыв",
                    onValueChange = { text = it }
                )
            }
        }
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
                "Айыл Банк",
                R.drawable.company,
                status
            )
        }
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            Column {
                BlueRectangularButton("Оставить отзыв") { dialogState = true }
                Spacer(Modifier.height(12.dp))
                BlueRectangularButton("Шаблон недельного отчета") { openFile(context, copyToDownloads(context, "Шаблон отчета.docx")) }
            }
        }
    }
}
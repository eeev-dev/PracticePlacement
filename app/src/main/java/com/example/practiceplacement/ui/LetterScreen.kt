package com.example.practiceplacement.ui

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.practiceplacement.R
import com.example.practiceplacement.ui.components.BlueCircularButton
import com.example.practiceplacement.ui.components.BlueRectangularButton
import com.example.practiceplacement.ui.components.LoadFile
import com.example.practiceplacement.ui.components.DownloadFile
import com.example.practiceplacement.ui.components.ExpandableBox
import com.example.practiceplacement.ui.components.Frame
import com.example.practiceplacement.ui.navigation.Appbar
import com.example.practiceplacement.ui.theme.sansFont
import com.example.practiceplacement.utils.copyToDownloads
import com.example.practiceplacement.utils.openImage
import com.example.practiceplacement.viewmodels.LetterViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LetterScreen(
    navController: NavController,
    viewModel: LetterViewModel = hiltViewModel()
) {
    val response = viewModel.result
    val context = LocalContext.current
    Appbar(navController, "Практика", onExit = {  }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            LazyColumn {
                item {
                    ExpandableBox(
                        R.drawable.flag,
                        "Инструкция",
                        "Обратитесь к ответственному сотруднику или руководителю практики на предприятиии. Предоставьте шаблон письма (см. ниже). Этот шаблон поможет ответственному лицу оформить официальное письмо. Когда оно бдует готово, отправьте его фото или скан через приложение."
                    )
                }
                item {
                    Spacer(Modifier.height(12.dp))
                }
                item {
                    LoadFile("Выбрать файл") { selectedFile ->
                        viewModel.sendPlace(context, selectedFile)
                        response?.onSuccess {
                            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                            viewModel.refreshStatus()
                            navController.navigate("practice_screen")
                        }?.onFailure {
                            Toast.makeText(context, "Ошибка: ${it.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                item {
                    Spacer(Modifier.height(12.dp))
                }
                item {
                    Frame(
                        title = "Посмотреть шаблон",
                        onClick = {
                            openImage(context, copyToDownloads(context, "letter.png"))
                        }
                    ) { title, onClick ->
                        DownloadFile(title, onClick)
                    }
                }
                item {
                    Spacer(Modifier.height(60.dp))
                }
            }
            Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                BlueRectangularButton("Отправить письмо") { }
            }
        }
    }
}
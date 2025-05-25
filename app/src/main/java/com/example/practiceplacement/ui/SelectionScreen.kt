package com.example.practiceplacement.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.practiceplacement.utils.formatDateRussian

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SelectionScreen(
    navController: NavController,
    deadline: LocalDate,
    viewModel: SelectionViewModel = hiltViewModel()
) {
    val places by viewModel.places.collectAsState()
    val message = ""
    Box(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Column {
            Message(message)
            Deadline(formatDateRussian(deadline))
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
            BlueRectangularButton("Свое место практики") { navController.navigate("letter_screen") }
        }
    }
}

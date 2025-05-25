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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.practiceplacement.data.remote.models.Place
import com.example.practiceplacement.ui.components.BlueRectangularButton
import com.example.practiceplacement.ui.components.Deadline
import com.example.practiceplacement.ui.components.Message
import com.example.practiceplacement.ui.tabs.company.CompanyItem
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SelectionScreen(
    navController: NavController,
    deadline: LocalDate
) {
    // val places by viewModel.places.collectAsState()
    val places = listOf(
        Place(0, "Айыл банк", "Web-программирование", 4),
        Place(1, "Оптима банк", "Мобильная разработка", 7)
    )
    val message = ""
    Box(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Column {
            Message(message)
            Deadline(deadline.toString())
            LazyColumn {
                places.forEach { place ->
                    item {
                        CompanyItem(place) { navController.navigate("about_screen/${place.id}") }
                    }
                }
                item {
                    Spacer(Modifier.height(70.dp))
                }
            }
        }
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            BlueRectangularButton("Свое место практики") { navController.navigate("letter_screen") }
        }
    }
}

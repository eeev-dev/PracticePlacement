package com.example.practiceplacement.ui.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.practiceplacement.ui.components.ClipboardTextField
import com.example.practiceplacement.ui.components.SendConfirmation
import com.example.practiceplacement.ui.tabs.review.RatingBar
import com.example.practiceplacement.ui.tabs.review.ReviewItem
import com.example.practiceplacement.ui.tabs.review.Statistics

@Composable
fun ReviewTab() {
    Column {
        val reviews = listOf(
            Pair(
                5,
                "Работал над реальными задачами, познакомился с классной командой, получил много нового опыта и знаний"
            ),
            Pair(3, "задачи были скучные и однотипные, практически не было обратной связи")
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            item {
                Spacer(modifier = Modifier.height(15.dp).background(Color.Transparent))
            }
            reviews.forEach { review ->
                item {
                    ReviewItem(
                        review.first,
                        review.second,
                        "12.07.24"
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(90.dp).background(Color.Transparent))
            }
        }
    }
}
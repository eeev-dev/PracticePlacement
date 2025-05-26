package com.example.practiceplacement.ui.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.practiceplacement.data.remote.models.Review
import com.example.practiceplacement.ui.tabs.review.ReviewItem
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ReviewTab(reviews: List<Review>) {
    Column {
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
                        review.rating,
                        review.text,
                        review.date
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(90.dp).background(Color.Transparent))
            }
        }
    }
}
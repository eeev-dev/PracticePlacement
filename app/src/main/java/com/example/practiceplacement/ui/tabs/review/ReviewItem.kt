package com.example.practiceplacement.ui.tabs.review

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practiceplacement.ui.theme.sansFont

@Composable
fun ReviewItem(
    rating: Int,
    review: String,
    date: String
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 12.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row {
                RatingBar(rating)
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(1f)
                ) {
                    Text(
                        text = date,
                        fontSize = 16.sp,
                        fontFamily = sansFont,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            Text(
                text = review,
                fontFamily = sansFont,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 15.dp, top = 8.dp)
            )
        }
    }
}
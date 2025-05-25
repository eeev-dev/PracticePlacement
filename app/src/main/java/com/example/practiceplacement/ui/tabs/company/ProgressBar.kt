package com.example.practiceplacement.ui.tabs.company

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practiceplacement.R
import com.example.practiceplacement.ui.theme.sansFont

@Composable
fun ProgressBar(
    max: Int,
    current: Int
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            var boxWidth by remember { mutableIntStateOf(0) }
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .height(30.dp)
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        boxWidth = coordinates.size.width
                    }
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(percent = 50)
                    )
                    .clip(RoundedCornerShape(percent = 50))
                    .border(
                        width = 1.dp,
                        color = colorResource(R.color.stroke_grey),
                        shape = RoundedCornerShape(percent = 50)
                    )
            ) {
                val density = LocalDensity.current
                val progressWidth = with(density) {
                    if (current != 0) {
                        (boxWidth / max * current).toDp()
                    } else {
                        0.toDp()
                    }
                }
                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .width(progressWidth)
                        .background(
                            color = colorResource(R.color.blue),
                            shape = CircleShape
                        )
                        .clip(RoundedCornerShape(percent = 50))
                )
            }
            Box(Modifier.fillMaxWidth().padding(end = 8.dp)) {
                Text(
                    text = "$current/$max",
                    fontFamily = sansFont,
                    fontSize = 20.sp,
                    color = colorResource(R.color.stroke_grey),
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
        }
    }
}
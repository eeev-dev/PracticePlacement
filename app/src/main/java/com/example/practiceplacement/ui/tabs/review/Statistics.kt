package com.example.practiceplacement.ui.tabs.review

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practiceplacement.ui.components.BlueCircularButton
import com.example.practiceplacement.ui.theme.sansFont

@Composable
fun Statistics(
    onOpenDialog: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, top = 8.dp, bottom = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = "4,8",
                fontSize = 32.sp,
                lineHeight = 32.sp,
                fontFamily = sansFont
            )
            Text(
                text = "4 отзыва",
                fontSize = 20.sp,
                lineHeight = 20.sp,
                fontFamily = sansFont
            )
        }
        Box(modifier = Modifier.align(Alignment.CenterVertically).weight(1f).padding(start = 4.dp)) {
            BlueCircularButton("Оставить отзыв") { onOpenDialog() }
        }
    }
}
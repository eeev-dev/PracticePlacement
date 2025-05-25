package com.example.practiceplacement.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.practiceplacement.R

@Composable
fun DownloadFile(
    title: String,
    onClick: () -> Unit
) {
    Row {
        Image(
            painter = painterResource(id = R.drawable.paperclip),
            contentDescription = "Скрепка",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.CenterVertically)
        )
        Box(
            modifier = Modifier.align(Alignment.CenterVertically).weight(1f)
                .padding(start = 8.dp)
        ) {
            BlueCircularButton(title, onClick)
        }
    }
}
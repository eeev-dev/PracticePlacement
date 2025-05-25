package com.example.practiceplacement.ui.components

import android.net.http.UrlRequest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practiceplacement.R
import com.example.practiceplacement.ui.theme.sansFont

@Composable
fun Success() {
    Warning(
        R.drawable.check_circle,
        colorResource(R.color.green_warning),
        "Проверка на уникальность пройдена"
    )
}

@Composable
fun Deadline(text: String) {
    Warning(
        R.drawable.clock,
        colorResource(R.color.red_warning),
        text
    )
}

@Composable
fun Failure() {
    Warning(
        R.drawable.alert,
        colorResource(R.color.red_warning),
        "Проверка на уникальность не пройдена"
    )
}

@Composable
fun Status(
    iconId: Int,
    color: Color,
    text: String
) {
    Box(Modifier.padding(top = 12.dp, start = 12.dp, end = 12.dp)) {
        Row(
            modifier = Modifier
                .padding(vertical = 5.dp)
                .background(
                    color = color,
                    shape = RoundedCornerShape(percent = 50)
                )
                .fillMaxWidth()
        ) {
            Icon(
                ImageVector.vectorResource(iconId),
                tint = Color.White,
                contentDescription = "Иконка",
                modifier = Modifier
                    .padding(horizontal = 2.dp)
            )
            Box(Modifier.padding(end = 12.dp).align(Alignment.CenterVertically).weight(1f)) {
                Text(
                    text = text,
                    fontFamily = sansFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .align(Alignment.Center),
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun Warning(
    iconId: Int,
    color: Color,
    text: String
) {
    Box(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .background(
                color = color,
                shape = RoundedCornerShape(percent = 50)
            )
            .fillMaxWidth()
    ) {
        Icon(
            ImageVector.vectorResource(iconId),
            tint = Color.White,
            contentDescription = "Иконка",
            modifier = Modifier
                .padding(horizontal = 2.dp)
        )
        Text(
            text = text,
            fontFamily = sansFont,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.Center),
            color = Color.White
        )
    }
}
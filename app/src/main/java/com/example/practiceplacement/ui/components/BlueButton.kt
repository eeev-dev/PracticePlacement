package com.example.practiceplacement.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practiceplacement.R

@Composable
fun BlueRectangularButton(
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorResource(R.color.blue),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick
            )
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.Center),
            color = Color.White,
            fontSize = 20.sp
        )
    }
}

@Composable
fun BlueCircularButton(
    textButton: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color = colorResource(R.color.blue),
                RoundedCornerShape(percent = 50)
            )
            .fillMaxWidth()
    ) {
        Text(
            text = textButton,
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(12.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = onClick
                )
        )
    }
}

@Composable
fun BlueFloatingButton() {

}
package com.example.practiceplacement.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.practiceplacement.R

@Composable
fun Frame(
    title: String = "",
    onClick: () -> Unit = {},
    content: @Composable (String, () -> Unit) -> Unit
) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = colorResource(R.color.border_for_card),
                shape = RoundedCornerShape(12.dp)
            )
            .background(Color.White)
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        content(title, onClick)
    }
}
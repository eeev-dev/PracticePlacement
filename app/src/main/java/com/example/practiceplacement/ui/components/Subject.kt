package com.example.practiceplacement.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practiceplacement.R
import com.example.practiceplacement.ui.theme.sansFont
import javax.security.auth.Subject

@Composable
fun Subject(
    title: String,
    details: String,
    iconId: Int,
    status: String?
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(12.dp)) {
            status?.let {
                if (status != "Подтвержден") Status(
                    R.drawable.clock,
                    colorResource(R.color.red_warning),
                    status
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(modifier = Modifier.align(Alignment.CenterVertically).weight(1f)) {
                    Icon(
                        imageVector = ImageVector.vectorResource(iconId),
                        contentDescription = "Иконка"
                    )
                }
                Spacer(Modifier.width(12.dp))
                Column(
                    modifier = Modifier.align(Alignment.CenterVertically).weight(4f)
                ) {
                    Text(
                        text = title,
                        fontSize = 20.sp,
                        lineHeight = 20.sp,
                        fontFamily = sansFont
                    )
                    Spacer(Modifier.height(12.dp))
                    BlueCircularButton(details) { }
                }
            }
        }
    }
}
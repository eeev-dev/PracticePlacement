package com.example.practiceplacement.ui.tabs.company

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practiceplacement.R
import com.example.practiceplacement.data.remote.models.Place
import com.example.practiceplacement.ui.components.BlueRectangularButton
import com.example.practiceplacement.ui.theme.sansFont

@Composable
fun CompanyItem(
    place: Place,
    navigate: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.padding(vertical = 6.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = place.title,
                fontFamily = sansFont,
                fontSize = 32.sp
            )
            if (place.occupation.isNotEmpty()) {
                Text(
                    text = place.occupation,
                    fontFamily = sansFont,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
            Text(
                text = "Мест: ${place.places}",
                fontFamily = sansFont,
                fontSize = 20.sp,
                color = colorResource(R.color.grey_for_text),
                modifier = Modifier.padding(bottom = 4.dp)
            )
            BlueRectangularButton("Подробности") { navigate() }
        }
    }
}
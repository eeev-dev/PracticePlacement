package com.example.practiceplacement.ui.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.practiceplacement.R
import com.example.practiceplacement.data.remote.models.Place
import com.example.practiceplacement.ui.components.ExpandableCard
import com.example.practiceplacement.ui.tabs.company.ProgressBar

@Composable
fun CompanyTab(place: Place) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(start = 12.dp, end = 12.dp)
    ) {
        item {
            ProgressBar(place.max_places, place.max_places - place.places)
        }
        item {
            Spacer(modifier = Modifier.height(6.dp).background(Color.Transparent))
        }
        item {
            ExpandableCard(
                R.drawable.user_check,
                "Требования",
                place.requirements
            )
        }
        item {
            ExpandableCard(
                R.drawable.zap,
                "Перспективы",
                place.outlook
            )
        }
        item {
            ExpandableCard(
                R.drawable.phone,
                "Контакты",
                place.contacts
            )
        }
        item {
            Spacer(modifier = Modifier.height(90.dp).background(Color.Transparent))
        }
    }
}
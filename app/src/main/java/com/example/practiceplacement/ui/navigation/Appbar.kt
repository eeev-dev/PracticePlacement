package com.example.practiceplacement.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.practiceplacement.R
import com.example.practiceplacement.ui.theme.sansFont

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Appbar(
    navController: NavController,
    text: String,
    content: @Composable () -> Unit
) {
    SetStatusBarColor(Color.Black)
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(R.color.blue))
                .padding(top = 12.dp, bottom = 12.dp)
        ) {
            Text(
                text = text,
                fontFamily = sansFont,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 15.dp),
                color = Color.White
            )
            Spacer(Modifier.weight(1f))
            SettingsMenu(navController)
        }
        Box(Modifier.weight(1f)) { content() }
    }
}
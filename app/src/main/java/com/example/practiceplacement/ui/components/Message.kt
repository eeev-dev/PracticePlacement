package com.example.practiceplacement.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practiceplacement.R

@Composable
fun Message(
    message: String,
    onClick: () ->  Unit = {}
) {
    var messageState by remember { mutableStateOf(message.isNotEmpty()) }
    AnimatedVisibility(
        visible = messageState,
        enter = expandVertically(
            animationSpec = tween(durationMillis = 300)
        ) + fadeIn(animationSpec = tween(300)),
        exit = shrinkVertically(
            animationSpec = tween(durationMillis = 200)
        ) + fadeOut(animationSpec = tween(200))
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
                .border(
                    width = 1.dp,
                    color = colorResource(R.color.red_warning),
                    shape = RoundedCornerShape(8.dp)
                )
                .background(
                    color = colorResource(R.color.red_message),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Column(Modifier.fillMaxWidth().padding(15.dp)) {
                Text(
                    text = message,
                    color = colorResource(R.color.red_warning),
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp)
                )
                Box(Modifier.fillMaxWidth()) {
                    Text(
                        text = "Ок",
                        color = colorResource(R.color.red_warning),
                        fontSize = 16.sp,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() },
                                onClick = {
                                    onClick()
                                    messageState = !messageState
                                }
                            )

                    )
                }
            }
        }
    }
}
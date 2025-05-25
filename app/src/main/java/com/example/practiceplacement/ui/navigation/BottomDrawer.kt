package com.example.practiceplacement.ui.navigation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.practiceplacement.R
import kotlinx.coroutines.launch

@Composable
fun BottomDrawer(
    icons: List<Pair<Int, String>>,
    pagerState: PagerState
) {
    val activeColor = colorResource(R.color.active_tab)
    val inactiveColor = colorResource(R.color.inactive_tab)
    val coroutineScope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .background(Color.White)
            .padding(vertical = 8.dp)
            .height(70.dp)
    ) {
        icons.forEachIndexed { index, icon ->
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f)
            ) {
                val isSelected = pagerState.currentPage == index
                val yOffset by animateDpAsState(
                    targetValue = if (pagerState.currentPage == index) (-4).dp else 0.dp,
                    animationSpec = tween(durationMillis = 250)
                )
                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .offset(y = yOffset)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                        ) {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                    imageVector = ImageVector.vectorResource(icon.first),
                    contentDescription = icon.second,
                    tint = if(isSelected) activeColor else inactiveColor
                )
                val circleSize by animateDpAsState(
                    targetValue = if (isSelected) 10.dp else 0.dp,
                    animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
                    label = "CircleSizeAnimation"
                )
                if (isSelected) {
                    Box(
                        modifier = Modifier
                            .padding(top = 2.dp)
                            .size(circleSize)
                            .background(colorResource(R.color.blue), CircleShape)
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}
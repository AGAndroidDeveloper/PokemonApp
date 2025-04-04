package com.ankit.pokedoxapp.ui.detail

import android.util.Log
import androidx.annotation.Size
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.ankit.pokedoxapp.ui.theme.PokeDoxAppTheme

@Composable
fun CustomProgressBar(
    modifier: Modifier = Modifier, progress: Int = 0,
    trackColor: Color? = null,
    progressColor: Color? = null
) {
    var animatedProgress by remember { mutableStateOf(0.5f) }

    LaunchedEffect(progress) {
        animatedProgress = progress / 100f
    }

    val animationSpec: AnimationSpec<Float> = spring(
        dampingRatio = Spring.DampingRatioHighBouncy,
        stiffness = Spring.StiffnessLow
    )

    val animatedWidth by animateFloatAsState(
        animationSpec = animationSpec,
        targetValue = animatedProgress,
        // animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing), // Smooth animation
        label = "progress_animation"
    )

    Box(
        modifier = modifier
            .height(30.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(color = trackColor ?: Color.DarkGray.copy(.7f))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(animatedWidth)
                .fillMaxHeight()
                .clip(RoundedCornerShape(50.dp))
                .background(color = progressColor ?: Color.Red)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(animatedWidth) // Animated width applied to Box
                .fillMaxHeight()
                .background(color = Color.Transparent),
            contentAlignment = Alignment.Center // Centers the Text inside the Box
        ) {
            Text(
                text = "$progress%",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color.Black,
                    textAlign = TextAlign.End, // Aligns text inside the Text itself
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp)
            )
        }


    }

}

@Composable
@Preview(showBackground = true)
fun ProgressBarPreview() {
    PokeDoxAppTheme {
        Surface(modifier = Modifier.height(200.dp), color = Color.Black) {
            Box(contentAlignment = Alignment.Center) {
                CustomProgressBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(20.dp),
                    progress = 45
                )
            }
        }
    }
}
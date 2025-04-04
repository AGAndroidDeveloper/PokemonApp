package com.ankit.pokedoxapp.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ankit.pokedoxapp.ui.theme.PokeDoxAppTheme

@Composable
fun PokemonStateProgress(
    modifier: Modifier = Modifier,
    statsType: String? = null,
    progress: Int? = 98,
    trackColor: Color? = null,
    progressColor: Color? = null
) {
    var currentProgress by remember { mutableStateOf(0f) }
    LaunchedEffect(currentProgress) {
        currentProgress = 45f
    }
    Row(
        modifier = modifier.padding(bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = statsType ?: "HP",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.White.copy(.4f),
                fontSize = 16.sp
            )
        )

        Spacer(
            modifier = Modifier
                .width(20.dp)// Ensure it has a height to be visible
                .background(color = Color.Cyan.copy(.3f))
        )

        Box(
            modifier = Modifier // Ensure it fills the available height
                .fillMaxWidth()
                .height(20.dp), contentAlignment = Alignment.Center
        ) {
            CustomProgressBar(
                modifier = Modifier.matchParentSize(),
                progress = progress?:0,
                trackColor = trackColor,
                progressColor = progressColor
            )
        }
    }


}

@Composable
@Preview(showBackground = true)
fun PokemonStateProgressPreView() {
    PokeDoxAppTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = Color.Black) {
            PokemonStateProgress()
        }

    }
}
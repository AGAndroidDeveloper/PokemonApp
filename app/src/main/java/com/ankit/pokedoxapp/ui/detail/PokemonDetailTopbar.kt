package com.ankit.pokedoxapp.ui.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ankit.pokedoxapp.domain.utill.Helper.serialnumberFormatter

@Composable
@OptIn(ExperimentalMaterial3Api::class)
 fun PokemonDetailTopBar(
    dominantColor: List<Color>,
    onBackClick: () -> Unit,
    index: Int?
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = dominantColor.random()),
        modifier = Modifier,
        title = {
            Text(
                text = "Pokemon Detail",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                onBackClick()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "back button",
                    tint = Color.White
                )
            }
        },
        actions = {
            Text(
                text = index.toString().serialnumberFormatter(),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(end = 10.dp),
                color = Color.White
            )
        })
}
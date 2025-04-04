package com.ankit.pokedoxapp.ui.detail

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.util.DebugLogger
import com.ankit.pokedoxapp.R
import com.ankit.pokedoxapp.data.model.PokemonResponseByName
import com.ankit.pokedoxapp.domain.model.PokemonInfo
import com.ankit.pokedoxapp.ui.theme.PokeDoxAppTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PokemonDetailContent(
    index: Int?,
    modifier: Modifier,
    dominantColor: List<Color>,
    imageUrl: String,
    data: PokemonInfo? = null,
    onBackClick: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            PokemonDetailTopBar(data?.name, dominantColor, index, onBackClick)
        },
        containerColor = Color.Black
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    bottom = paddingValues.calculateBottomPadding(),
                    top = paddingValues.calculateTopPadding()
                ),
            verticalArrangement = Arrangement.Top
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                colors = CardDefaults.cardColors(containerColor = dominantColor.random()),
                shape = RoundedCornerShape(bottomEnd = 70.dp, bottomStart = 70.dp)
            ) {
                val imageLoader = ImageLoader.Builder(LocalContext.current)
                    .logger(DebugLogger())
                    .build()

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(30.dp),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = imageUrl,
                        imageLoader = imageLoader,
                        contentScale = ContentScale.Fit,
                        contentDescription = "Pokemon Image",
                        modifier = Modifier.fillMaxSize(),
                        placeholder = painterResource(R.drawable.charizard_seeklogo),
                    )
                }
            }
            detailBottomContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f), data
            )
        }
    }

}


@Composable
//@PreviewScreenSizes
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun PokemonPreview() {
    PokeDoxAppTheme {
        Surface {
            val randomImAGEuRL = (0..500).random()
            val imageUrl =
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$randomImAGEuRL.png"

            PokemonDetailContent(
                index = 23,
                modifier = Modifier.fillMaxSize(),
                dominantColor = listOf(Color.Yellow),
                imageUrl = imageUrl,
                data = PokemonInfo(name = "pokemon")
            ) {

            }
        }
    }

}


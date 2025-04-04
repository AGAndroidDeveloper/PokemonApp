package com.ankit.pokedoxapp.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ankit.pokedoxapp.domain.model.PokemonInfo
import com.ankit.pokedoxapp.domain.utill.Helper.pokemonData
import com.ankit.pokedoxapp.ui.theme.PokeDoxAppTheme

@Composable
fun detailBottomContent(modifier: Modifier = Modifier, data: PokemonInfo?) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "${data?.name}".replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold
            ),
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp), contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .wrapContentWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                data?.types?.forEachIndexed { index, type ->
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .clip(CircleShape)
                            .background(color = Color.Cyan.copy(.3f))
                            .padding(horizontal = 30.dp, vertical = 10.dp),
                        text = type.type.name,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White.copy(.7f),
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PokemonCharacterStiComposable(
                modifier = Modifier
                    .wrapContentHeight(),
                value = "${data?.weight?.div(10)} KG",
                title = "Weight"
            )

            PokemonCharacterStiComposable(
                modifier = Modifier
                    .wrapContentHeight(),
                value = "${data?.height?.div(10)} M",
                title = "Height"
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            text = "Base Stats",
            style = MaterialTheme.typography.titleMedium.copy(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        )
        Spacer(
            modifier = Modifier
                .height(10.dp)
                .fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            data?.stats?.forEachIndexed { index, states ->
                PokemonStateProgress(
                    statsType = states.name,
                    progress = states.baseStat?.let { if (it < 100) states.baseStat else 100 },
                    progressColor = Color.Green
                )
            }

//            PokemonStateProgress(progress = 35, progressColor = Color.Yellow)
//            PokemonStateProgress(progress = 23, progressColor = Color.Blue)
//            PokemonStateProgress(progress = 95, progressColor = Color.Magenta)
//            PokemonStateProgress(progress = 53, progressColor = Color.Cyan)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {

        }

    }
}

@Composable
fun PokemonCharacterStiComposable(
    modifier: Modifier,
    value: String? = null,
    title: String? = null,
) {
    Column(
        modifier = Modifier, verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$value",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                color = Color.White,
                fontStyle = FontStyle.Normal
            ),
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "$title",
            style = MaterialTheme.typography.titleMedium.copy(
                color = Color.White.copy(.4f),
                textAlign = TextAlign.Center
            ),
        )

    }
}


@Composable
@Preview(showBackground = true)
fun PokemonBottomContent() {
    PokeDoxAppTheme {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black)
                    .padding(20.dp)
            ) {
                detailBottomContent(data = pokemonData)
            }
        }
    }

}
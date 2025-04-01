package com.ankit.pokedoxapp.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ankit.pokedoxapp.data.model.PokemonResponseByName
import com.ankit.pokedoxapp.ui.theme.PokeDoxAppTheme

@Composable
fun ColumnScope.detailBottomContent(data: PokemonResponseByName?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .weight(1f)
            .padding(10.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "${data?.name}".replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.wrapContentWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            data?.types?.forEachIndexed { index, type ->
                Text(
                    modifier = Modifier
                        .background(color = Color.Red.copy(.2f), shape = RoundedCornerShape(25.dp))
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    text = type.type.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White.copy(.7f),
                )
            }
        }

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
                detailBottomContent(data = PokemonResponseByName(name = "pokemon"))
            }
        }
    }

}
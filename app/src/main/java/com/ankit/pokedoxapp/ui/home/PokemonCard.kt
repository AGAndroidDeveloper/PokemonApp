package com.ankit.pokedoxapp.ui.home

import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import coil3.Bitmap
import coil3.ImageLoader
import coil3.asDrawable
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.request.ImageRequest
import coil3.request.SuccessResult
import coil3.request.allowHardware
import coil3.request.crossfade
import coil3.request.transformations
import coil3.transform.CircleCropTransformation
import coil3.util.DebugLogger
import com.ankit.pokedoxapp.R
import com.ankit.pokedoxapp.data.model.Pokemon
import com.ankit.pokedoxapp.data.model.samplePokemonResponse
import com.ankit.pokedoxapp.domain.PaletteGenerator
import com.ankit.pokedoxapp.domain.PaletteGenerator.convertImageUrlToBitmap
import com.ankit.pokedoxapp.domain.PaletteGenerator.createPaletteAsync
import com.ankit.pokedoxapp.domain.PaletteGenerator.getBitMapFromUrlImage
import com.ankit.pokedoxapp.ui.theme.PokeDoxAppTheme

@Composable
fun PokemonCard(modifier: Modifier, index: Int, onPokemonClick: (Int) -> Unit) {
    val dominantColor = remember {
        mutableStateOf(Color.Gray)
    }

    val context = LocalContext.current

    val imageUrl =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${index + 1}.png"

    LaunchedEffect(imageUrl) {
        getBitMapFromUrlImage(context, imageUrl)
        val bitmap = convertImageUrlToBitmap(imageUrl, context)
        Log.e("TAG", "PokemonCardBitmap: $bitmap",)
        bitmap?.let {
//            val mappedColor = it.createPaletteAsync()
//            dominantColor.value = mappedColor
            Palette.from(it).generate { palette ->
                palette?.dominantSwatch?.rgb?.let { colorValue ->
                    dominantColor.value = Color(colorValue)
                }


            }
        }

    }

    ElevatedCard(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 10.dp)
            .clickable(true) {
                onPokemonClick(index)
            },
        colors = CardDefaults.elevatedCardColors(
            containerColor = dominantColor.value
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(26.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
//                val imageUrl =
//                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/11.png"
                // "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${index+1}.png"
                val imageLoader = ImageLoader.Builder(LocalContext.current)
                    .logger(DebugLogger())
                    .build()

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .build(),
                    imageLoader = imageLoader,
                    contentScale = ContentScale.Fit,
                    contentDescription = "Pokemon Image",
                    modifier = Modifier.fillMaxSize(),
                    placeholder = painterResource(R.drawable.charizard_seeklogo),
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Charzard $index",
                style = MaterialTheme.typography.labelSmall.copy(fontSize = 16.sp),
                textAlign = TextAlign.Center
            )
        }


    }
}


@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun PokemonPreview() {
    PokeDoxAppTheme {
        Surface {
            PokemonCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                1
            ) {

            }
        }

    }
}
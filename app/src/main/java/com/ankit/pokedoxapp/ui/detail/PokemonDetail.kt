package com.ankit.pokedoxapp.ui.detail

import android.content.res.Configuration
import android.os.Build.VERSION.SDK_INT
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.palette.graphics.Palette
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.gif.AnimatedImageDecoder
import coil3.gif.GifDecoder
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.size.Size
import coil3.util.DebugLogger
import com.ankit.pokedoxapp.R
import com.ankit.pokedoxapp.domain.utill.Helper.serialnumberFormatter
import com.ankit.pokedoxapp.domain.utill.PaletteGenerator.convertImageUrlToBitmap
import com.ankit.pokedoxapp.domain.utill.Result
import com.ankit.pokedoxapp.ui.theme.PokeDoxAppTheme
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    index: Int? = null,
    viewmodel: PokemonDetailViewmodel = koinViewModel(),
    onBackClick: () -> Unit = {}
) {
    val context = LocalContext.current
    val dominantColor = remember {
        mutableStateOf(listOf(Color.Black))
    }
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(AnimatedImageDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    val imageUrl =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"

    LaunchedEffect(imageUrl) {
        viewmodel.getPokemonByName(index.toString())
        val bitmap = convertImageUrlToBitmap(imageUrl, context)
        Log.e("TAG", "PokemonCardBitmap: $bitmap")
        bitmap?.let { it ->
            Palette.from(it).generate { palette ->
                val colorLIST = palette?.swatches?.map { c ->
                    Color(c.rgb)
                }
                dominantColor.value = colorLIST!!
            }
        }
    }

    val state = viewmodel.pokemonState.collectAsStateWithLifecycle()
    when (state.value) {
        is Result.Error<*> -> {
            Log.e("TAG", "PokemonDetailScreen: ${state.value}")
        }

        Result.Idle -> {

        }

        Result.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(context)
                            .data(data = R.drawable.loading_lotte_animation).apply(block = {
                                size(Size.ORIGINAL)
                            }).build(), imageLoader = imageLoader
                    ),
                    contentDescription = null,
                    modifier = modifier.fillMaxWidth(),
                )
            }
        }

        is Result.Success<*> -> {
            val data =
                (state.value as Result.Success<*>).data as com.ankit.pokedoxapp.data.model.PokemonResponseByName
            Log.e("TAG", "PokemonDetailScreen: $data")
            PokemonDetailContent(index, modifier, dominantColor.value, imageUrl, data, onBackClick)

        }
    }


}

package com.ankit.pokedoxapp.ui.home

import android.content.res.Resources
import android.util.Log
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.ankit.pokedoxapp.data.model.samplePokemonResponse
import com.ankit.pokedoxapp.ui.theme.PokeDoxAppTheme
import com.ankit.pokedoxapp.videmodel.PokeMonViewmodel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import com.ankit.pokedoxapp.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun PokemonListScreen(
    modifier: Modifier,
//    viewmodel: PokeMonViewmodel = koinViewModel(),
    onPokemonClick: (Int) -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    scope.launch {
        //  viewmodel.getPokemonByName("ivysaur")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.app_name),
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    titleContentColor = Color.White,
                    containerColor = Color.Red.copy(alpha = .6f)
                )
            )
        },
        containerColor = Color.Black
    ) { paddingValues ->
        LazyVerticalGrid(
            modifier = Modifier.padding(top = paddingValues.calculateTopPadding()),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(30, key = {
                it
            }) { it ->
                PokemonCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp), it
                ){
                    onPokemonClick(it)
                }
            }
        }
    }
    
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun pokemonListScreenPreview() {
    PokeDoxAppTheme {
       // PokemonListScreen(modifier = Modifier.fillMaxSize())
    }


}
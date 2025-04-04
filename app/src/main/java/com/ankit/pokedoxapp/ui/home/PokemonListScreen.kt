package com.ankit.pokedoxapp.ui.home

import android.util.Log
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import com.ankit.pokedoxapp.R
import com.ankit.pokedoxapp.ui.theme.PokeDoxAppTheme
import com.ankit.pokedoxapp.videmodel.PokeMonViewmodel
import org.koin.androidx.compose.koinViewModel

import androidx.paging.compose.collectAsLazyPagingItems

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun PokemonListScreen(
    modifier: Modifier,
    viewmodel: PokeMonViewmodel = koinViewModel(),
    onPokemonClick: (Int) -> Unit = {}
) {
    val pokemon = viewmodel.response.collectAsLazyPagingItems()
    Log.e("PokemonListScreen", "loadState: ${pokemon.loadState}")
    val listState = rememberLazyGridState()

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
            state = listState,
            modifier = Modifier.padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            ),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(pokemon.itemCount, key = { it ->
                Log.e("PokemonListScreen", "key: $it")
                pokemon[it]!!.id
            }) { it ->
                PokemonCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp), pokemon[it]
                ) {
                    onPokemonClick(it)
                }
            }

        }

    }

}

@Composable
fun RetryItem(retry: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Failed to load data. Tap to retry.")
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = retry) {
                Text(text = "Retry")
            }
        }
    }
}

@Composable
fun LoadingItem() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun pokemonListScreenPreview() {
    PokeDoxAppTheme {
        // PokemonListScreen(modifier = Modifier.fillMaxSize())
    }


}
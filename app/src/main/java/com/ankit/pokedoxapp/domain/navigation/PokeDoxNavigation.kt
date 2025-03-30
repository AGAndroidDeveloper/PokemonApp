package com.ankit.pokedoxapp.domain.navigation

import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.ankit.pokedoxapp.domain.Destination
import com.ankit.pokedoxapp.ui.detail.PokemonDetailScreen
import com.ankit.pokedoxapp.ui.home.PokemonListScreen

@Composable
fun PokeDoxNavigation(
    navController: NavHostController,
    startDestination: Destination = Destination.Home
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable<Destination.Home> {
            PokemonListScreen(modifier = Modifier.safeGesturesPadding()) { id ->
                navController.navigate(Destination.Detail(id))
            }
        }

        composable<Destination.Detail> {
            val detail: Destination.Detail = it.toRoute()
            PokemonDetailScreen(index = detail.index){
                navController.popBackStack()
            }
        }

    }
}
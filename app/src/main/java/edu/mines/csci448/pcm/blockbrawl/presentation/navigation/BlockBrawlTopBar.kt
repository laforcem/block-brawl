package edu.mines.csci448.pcm.blockbrawl.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import edu.mines.csci448.pcm.blockbrawl.presentation.navigation.specs.IScreenSpec
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.BlockBrawlViewModel

@Composable
fun BlockBrawlTopBar(blockBrawlViewModel: BlockBrawlViewModel,
                     navController: NavHostController,
                     context: Context
){
    val navBackStackEntryState = navController.currentBackStackEntryAsState()
    IScreenSpec.TopBar(blockBrawlViewModel,
        navController,
        navBackStackEntryState.value,
        context)
}
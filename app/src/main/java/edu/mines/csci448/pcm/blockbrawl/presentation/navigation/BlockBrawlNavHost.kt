package edu.mines.csci448.pcm.blockbrawl.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import edu.mines.csci448.pcm.blockbrawl.presentation.navigation.specs.IScreenSpec
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.BlockBrawlViewModel

@Composable
fun BlockBrawlNavHost(modifier: Modifier = Modifier,
                      navController: NavHostController,
                      blockBrawlViewModel: BlockBrawlViewModel,
                      context: Context
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = IScreenSpec.root
    ) {
        navigation(
            route = IScreenSpec.root,
            startDestination = IScreenSpec.startDestination
        ) {
            IScreenSpec.allScreens.forEach { (_, screen) ->
                if(screen != null) {
                    composable(
                        route = screen.route,
                        arguments = screen.arguments
                    ) {navBackStackEntry ->
                        screen.Content(
                            navController = navController,
                            navBackStackEntry = navBackStackEntry,
                            blockBrawlViewModel = blockBrawlViewModel,
                            context = context
                        )
                    }
                }
            }
        }
    }
}
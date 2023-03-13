package edu.mines.csci448.pcm.blockbrawl.presentation.navigation.specs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import edu.mines.csci448.pcm.blockbrawl.presentation.navigation.mainmenu.MainMenuScreen
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.BlockBrawlViewModel

object MainMenuScreenSpec : IScreenSpec{
    private const val LOG_TAG = "448.MainMenuScreenSpec"

    override val route = "mainMenu"
    override val arguments: List<NamedNavArgument> = emptyList()
    override fun buildRoute(vararg args: String?) = route

    @Composable
    override fun Content(
        blockBrawlViewModel: BlockBrawlViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry,
        context: Context
    ) {
        MainMenuScreen(
            blockBrawlViewModel,
            { navController.navigate( ProfileScreenSpec.route ) },
            { navController.navigate( GameScreenSpec.route ) },
            { navController.navigate( LeaderboardListScreenSpec.route ) },
            { navController.navigate( SettingsScreenSpec.route ) }
        )
    }
}
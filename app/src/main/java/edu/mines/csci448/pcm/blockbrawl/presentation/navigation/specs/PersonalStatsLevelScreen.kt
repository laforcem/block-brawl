package edu.mines.csci448.pcm.blockbrawl.presentation.navigation.specs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import edu.mines.csci448.pcm.blockbrawl.presentation.profilescreen.PersonalStatsLevelScreen
import edu.mines.csci448.pcm.blockbrawl.presentation.profilescreen.PersonalStatsScreen
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.IBlockBrawlViewModel

object PersonalStatsLevelScreen : IScreenSpec{
    private const val LOG_TAG = "448.PersonalStatsLevelScreen"

    override val title = "Best Level Stats"
    override val route = "personalLevelStats"
    override val arguments: List<NamedNavArgument> = emptyList()
    override fun buildRoute(vararg args: String?) = route
    override fun buildTitle() = title;
    @Composable
    override fun Content(
        blockBrawlViewModel: IBlockBrawlViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry,
        context: Context
    ) {
        PersonalStatsLevelScreen(
            blockBrawlViewModel = blockBrawlViewModel,
            onBackClicked = { navController.navigateUp() }
        )
    }
}
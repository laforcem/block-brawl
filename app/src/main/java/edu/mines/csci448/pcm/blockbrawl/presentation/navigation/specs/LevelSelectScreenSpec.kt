package edu.mines.csci448.pcm.blockbrawl.presentation.navigation.specs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import edu.mines.csci448.pcm.blockbrawl.presentation.leaderboardlistscreen.LeaderboardListScreen
import edu.mines.csci448.pcm.blockbrawl.presentation.leaderboardlistscreen.LevelSelectScreen
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.BlockBrawlViewModel
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.IBlockBrawlViewModel

object LevelSelectScreenSpec : IScreenSpec{
    private const val LOG_TAG = "448.LevelSelectSpec"

    override val route = "levelselectListScreen"
    override val title = "Level Select"
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
        LevelSelectScreen { navController.navigate(GameScreenSpec.buildRoute(it.toString())) }
    }

    @Composable
    override fun TopAppBarActions(
        blockBrawlViewModel: IBlockBrawlViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
        context: Context
    ){


    }
}
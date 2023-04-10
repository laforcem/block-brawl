package edu.mines.csci448.pcm.blockbrawl.presentation.navigation.specs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import edu.mines.csci448.pcm.blockbrawl.presentation.leaderboardlistscreen.LeaderboardListScreen
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.BlockBrawlViewModel

object LeaderboardListScreenSpec : IScreenSpec{
    private const val LOG_TAG = "448.LeaderboardListSpec"

    override val route = "leaderboardListScreen"
    override val title = "Leaderboard"
    override val arguments: List<NamedNavArgument> = emptyList()
    override fun buildRoute(vararg args: String?) = route
    override fun buildTitle() = title;
    @Composable
    override fun Content(
        blockBrawlViewModel: BlockBrawlViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry,
        context: Context
    ) {
        LeaderboardListScreen(
            blockBrawlViewModel = blockBrawlViewModel,
            onBackClicked = { navController.navigateUp() },
            onLeaderBoardItemClicked = { navController.navigate( DetailedLeaderboardListSpec.route ) }
        )
    }

    @Composable
    override fun TopAppBarActions(
        blockBrawlViewModel: BlockBrawlViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
        context: Context
    ){


    }
}
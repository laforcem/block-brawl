package edu.mines.csci448.pcm.blockbrawl.presentation.navigation.specs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import edu.mines.csci448.pcm.blockbrawl.presentation.navigation.LeaderboardListScreen.LeaderboardListScreen
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.BlockBrawlViewModel

object LeaderboardListScreenSpec : IScreenSpec{
    private const val LOG_TAG = "448.LeaderboardListSpec"

    override val route = "leaderboardListScreen"
    override val arguments: List<NamedNavArgument> = emptyList()
    override fun buildRoute(vararg args: String?) = route

    @Composable
    override fun Content(
        blockBrawlViewModel: BlockBrawlViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry,
        context: Context
    ) {
        LeaderboardListScreen(
            blockBrawlViewModel = BlockBrawlViewModel(),
            onBackClicked = { navController.navigateUp() },
            onLeaderBoardItemClicked = { navController.navigate( DetailedLeaderboardListSpec.route ) }
        )
    }
}
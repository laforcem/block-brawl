package edu.mines.csci448.pcm.blockbrawl.presentation.navigation.specs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import edu.mines.csci448.pcm.blockbrawl.presentation.leaderboardlistscreen.DetailedLeaderboardListScreen
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.BlockBrawlViewModel

object DetailedLeaderboardListSpec : IScreenSpec{
    private const val LOG_TAG = "448.DetailedLeaderboardListSpec"


    override val route = "detailedLeaderboardList"
    override val title = "Detail"
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
        DetailedLeaderboardListScreen(
            blockBrawlViewModel = blockBrawlViewModel,
            onBackClicked = { navController.navigateUp() }
        )
    }
}
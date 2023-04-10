package edu.mines.csci448.pcm.blockbrawl.presentation.navigation.specs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.*
import edu.mines.csci448.pcm.blockbrawl.presentation.gamescreen.GameScreen
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.BlockBrawlViewModel

object GameScreenSpec : IScreenSpec {
    private const val LOG_TAG = "448.GameScreenSpec"

    override val title = "Block Brawl"
    override val route = "gameScreen"
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
        GameScreen(
            blockBrawlViewModel = blockBrawlViewModel,
            onPauseClicked = {navController.navigateUp()}
        )
    }

}
package edu.mines.csci448.pcm.blockbrawl.presentation.navigation.specs

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.navigation.*
import edu.mines.csci448.pcm.blockbrawl.R
import edu.mines.csci448.pcm.blockbrawl.data.BlockBrawlLevel
import edu.mines.csci448.pcm.blockbrawl.presentation.gamescreen.Block
import edu.mines.csci448.pcm.blockbrawl.presentation.gamescreen.GameScreen
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.BlockBrawlViewModel
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.IBlockBrawlViewModel

object GameScreenSpec : IScreenSpec {
    private const val LOG_TAG = "448.GameScreenSpec"
    private const val ARG_LEVEL = "level"

    override val title = "Block Brawl"
    private val route_base = "gameScreen"
    override val arguments: List<NamedNavArgument> = listOf(
        navArgument(ARG_LEVEL) {
            type = NavType.StringType
        }
    )
    override fun buildTitle() = title;

    private fun buildRouteFromLevel(argVal: String): String {
        var fullRoute = route_base
        if(argVal == ARG_LEVEL) {
            fullRoute += "/{$argVal}"
        } else {
            fullRoute += "/$argVal"
        }
        return fullRoute
    }

    override val route = buildRouteFromLevel(ARG_LEVEL)

    override fun buildRoute(vararg args: String?) = buildRouteFromLevel(args[0] ?: "1")


    @Composable
    override fun Content(
        blockBrawlViewModel: IBlockBrawlViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry,
        context: Context
    ) {


        val level = navBackStackEntry.arguments?.getString(ARG_LEVEL, "1")?.toInt()
        val username = blockBrawlViewModel.username.collectAsState().value

        if (level != null) {
            GameScreen(
                blockList = blockBrawlViewModel.getBlockList(level),
                onGameOver = {
                    blockBrawlViewModel.addLevelStats(BlockBrawlLevel(userName = username, completed = true, levelNumber = level, score = it))
                },
                context,
                blockBrawlViewModel.musicPlayer
            )
        }
    }

}
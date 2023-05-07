package edu.mines.csci448.pcm.blockbrawl.presentation.navigation.specs

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.*
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

        // This is just a temporary test block
        val block1 = Block(
            3,
            0,
            true,
            arrayOf(
                arrayOf('C'),
                arrayOf('X')
            ),
            5
        )

        val block2 = Block(
            1,
            0,
            true,
            arrayOf(
                arrayOf('X', 'C'),
                arrayOf(' ', 'X')
            ),
            20
        )

        val block3 = Block(
            1,
            1,
            false,
            arrayOf(
                arrayOf('X', 'X', ' ', ' '),
                arrayOf(' ', 'C', 'X', ' '),
                arrayOf(' ', ' ', 'X', 'X')
            ),
            30
        )

        val block4 = Block(
            2,
            2,
            true,
            arrayOf(
                arrayOf(' ', 'X', ' '),
                arrayOf(' ', 'X', ' '),
                arrayOf('X', 'C', ' '),
                arrayOf('X', ' ', ' ')
            ),
            15
        )

        val block5 = Block(
            4,
            1,
            false,
            arrayOf(
                arrayOf('C', 'X'),
                arrayOf('X', 'X'),
            ),
            10
        )

        val block6 = Block(
            5,
            1,
            true,
            arrayOf(
                arrayOf(' ', 'X'),
                arrayOf('X', 'C')
            ),
            15
        )

        val block7 = Block(
            4,
            2,
            true,
            arrayOf(
                arrayOf('X', 'C', 'X'),
                arrayOf('X', ' ', ' ')
            ),
            10
        )

        val block8 = Block(
            4,
            0,
            false,
            arrayOf(
                arrayOf('C', 'X'),
            ),
            5
        )


        val level = navBackStackEntry.arguments?.getString(ARG_LEVEL, "1")

        GameScreen(
            blockList = listOf(block1, block2, block3, block4, block5, block6, block7, block8),
            onGameOver = {
                //blockBrawlViewModel.addLevelStats()
            }
        )
    }

}
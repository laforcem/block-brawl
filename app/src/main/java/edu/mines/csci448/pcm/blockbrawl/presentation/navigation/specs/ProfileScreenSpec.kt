package edu.mines.csci448.pcm.blockbrawl.presentation.navigation.specs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import edu.mines.csci448.pcm.blockbrawl.presentation.profilescreen.ProfileScreen
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.IBlockBrawlViewModel

object ProfileScreenSpec : IScreenSpec {
    private const val LOG_TAG = "448.ProfileScreenSpec"

    override val route = "profileScreen"
    override val title = "Profile"
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
        ProfileScreen(
            onStatsClicked = { navController.navigate(PersonalStatsScreenSpec.route) },
            blockBrawlViewModel = blockBrawlViewModel
        )
    }

    @Composable
    override fun TopAppBarActions(
        blockBrawlViewModel: IBlockBrawlViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
        context: Context
    ) {

    }


}
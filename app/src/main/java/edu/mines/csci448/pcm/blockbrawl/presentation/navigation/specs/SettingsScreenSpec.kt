package edu.mines.csci448.pcm.blockbrawl.presentation.navigation.specs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import edu.mines.csci448.pcm.blockbrawl.presentation.settings.SettingsScreen
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.BlockBrawlViewModel
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.IBlockBrawlViewModel
import edu.mines.olsgard_a4.util.DataStoreManager

object SettingsScreenSpec : IScreenSpec{
    private const val LOG_TAG = "448.SettingsScreenSpec"

    override val title = "Settings"
    override val route = "settingsScreen"
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
        SettingsScreen(context)
    }
}
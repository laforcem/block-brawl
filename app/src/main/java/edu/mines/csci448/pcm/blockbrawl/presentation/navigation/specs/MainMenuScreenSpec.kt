package edu.mines.csci448.pcm.blockbrawl.presentation.navigation.specs

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import edu.mines.csci448.pcm.blockbrawl.R
import edu.mines.csci448.pcm.blockbrawl.presentation.mainmenu.MainMenuScreen
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.BlockBrawlViewModel
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.IBlockBrawlViewModel

object MainMenuScreenSpec : IScreenSpec{
    private const val LOG_TAG = "448.MainMenuScreenSpec"

    override val route = "mainMenu"
    override val title = ""
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
        MainMenuScreen(
            blockBrawlViewModel,
            { navController.navigate( LevelSelectScreenSpec.route ) },
            { navController.navigate( LeaderboardListScreenSpec.route ) },
            { navController.navigate( SettingsScreenSpec.route ) }
        )
    }


    @Composable
    override fun TopAppBarActions(
        blockBrawlViewModel: IBlockBrawlViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
        context: Context
    ){
        IconButton(onClick = { navController.navigate( ProfileScreenSpec.route ) }, modifier = Modifier.size(80.dp)) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = stringResource(R.string.menu_profile_desc),
                modifier = Modifier.padding(12.dp).size(80.dp)
            )
        }

    }

}
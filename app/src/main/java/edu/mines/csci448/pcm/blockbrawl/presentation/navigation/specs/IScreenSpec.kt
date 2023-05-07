package edu.mines.csci448.pcm.blockbrawl.presentation.navigation.specs

import android.content.Context
import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.BlockBrawlViewModel
import edu.mines.csci448.pcm.blockbrawl.R

sealed interface IScreenSpec {
    companion object {
        private const val LOG_TAG = "448.IScreenSpec"


        val allScreens = IScreenSpec::class.sealedSubclasses.associate {
            Log.d(LOG_TAG, "allScreens: mapping route \"${it.objectInstance?.route ?: ""}\" to object \"${it.objectInstance}\"")
            it.objectInstance?.route to it.objectInstance
        }
        const val root = "blockBrawl"
        val startDestination = MainMenuScreenSpec.route

        @Composable
        public fun TopBar(
            blockBrawlViewModel: BlockBrawlViewModel,
            navController: NavHostController,
            navBackStackEntry: NavBackStackEntry?,
            context: Context
        ){
            val route = navBackStackEntry?.destination?.route ?: ""
            allScreens[route]?.TopAppBarContent(blockBrawlViewModel,
                navController,
                navBackStackEntry,
                context)
        }

    }
    val title: String
    val route: String
    val arguments: List<NamedNavArgument>

    fun buildRoute(vararg args: String?): String
    fun buildTitle(): String

    @Composable
    fun Content(
        blockBrawlViewModel: BlockBrawlViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry,
        context: Context
    )


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun TopAppBarContent(
        blockBrawlViewModel: BlockBrawlViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
        context: Context
    ){
        CenterAlignedTopAppBar(navigationIcon = if (navController.previousBackStackEntry != null) {
            {

                IconButton(onClick = {blockBrawlViewModel.changeTitleText(navController.previousBackStackEntry?.destination?.route); navController.navigateUp(); }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.app_bar_back_desc)
                    )
                }
            }
        } else {
            { }
        },

            title = { Text(
                text = buildTitle(),
                style = TextStyle(fontWeight = FontWeight.Bold),
                fontSize = 30.sp,
                modifier = Modifier
            ) },

            actions = { TopAppBarActions(blockBrawlViewModel,
                navController,
                navBackStackEntry,
                context) }

        )
    }

    @Composable
    fun TopAppBarActions(
        blockBrawlViewModel: BlockBrawlViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
        context: Context
    ) {
    }

}


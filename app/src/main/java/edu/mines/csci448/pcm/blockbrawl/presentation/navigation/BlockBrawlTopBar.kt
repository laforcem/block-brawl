package edu.mines.csci448.pcm.blockbrawl.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import edu.mines.csci448.pcm.blockbrawl.presentation.navigation.specs.IScreenSpec
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.IBlockBrawlViewModel
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.PreviewBlockBrawlViewModel

@Composable
fun BlockBrawlTopBar(
    blockBrawlViewModel: IBlockBrawlViewModel,
    navController: NavHostController,
    context: Context
) {
    val navBackStackEntryState = navController.currentBackStackEntryAsState()
    IScreenSpec.TopBar(
        blockBrawlViewModel,
        navController,
        navBackStackEntryState.value,
        context
    )
}

@Preview(showBackground = true)
@Composable
fun BlockBrawlTopBarPreview() {
    val context = LocalContext.current
    BlockBrawlTopBar(
        blockBrawlViewModel = PreviewBlockBrawlViewModel(),
        navController = NavHostController(context),
        context = context
    )
}
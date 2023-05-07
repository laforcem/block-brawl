package edu.mines.csci448.pcm.blockbrawl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import edu.mines.csci448.pcm.blockbrawl.presentation.navigation.BlockBrawlNavHost
import edu.mines.csci448.pcm.blockbrawl.presentation.navigation.BlockBrawlTopBar
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.BlockBrawlViewModel
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.BlockBrawlViewModelFactory
import edu.mines.csci448.pcm.blockbrawl.ui.theme.BlockBrawlTheme

class MainActivity : ComponentActivity() {

    private lateinit var mBlockBrawlViewModel: BlockBrawlViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //create factory & view model
        val factory = BlockBrawlViewModelFactory(this)
        mBlockBrawlViewModel = ViewModelProvider(
            this,
            factory
        )[factory.getViewModelClass()] //TODO: have factory create view model

        setContent {
            val navController = rememberNavController()
            mBlockBrawlViewModel.navController = navController
            val context = LocalContext.current

            BlockBrawlTheme {
                Scaffold(topBar = {
                    BlockBrawlTopBar(
                        mBlockBrawlViewModel,
                        navController,
                        context
                    )
                }) { padding ->
                    BlockBrawlNavHost(
                        modifier = Modifier.padding(padding),
                        navController,
                        mBlockBrawlViewModel,
                        context
                    )
                }
            }
        }
    }
}

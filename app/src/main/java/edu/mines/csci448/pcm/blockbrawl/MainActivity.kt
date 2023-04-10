package edu.mines.csci448.pcm.blockbrawl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
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
        mBlockBrawlViewModel = ViewModelProvider(this, factory)[factory.getViewModelClass()] //TODO: have factory create view model

        setContent {
            val navController = rememberNavController()
            val context = LocalContext.current
            Scaffold(topBar = { BlockBrawlTopBar(mBlockBrawlViewModel, navController, context) }) { padding ->
                BlockBrawlNavHost(modifier = Modifier.padding(padding), navController, mBlockBrawlViewModel, context) }
            //BlockBrawlTheme {
                // A surface container using the 'background' color from the theme
              //  Surface(
               //     modifier = Modifier.fillMaxSize(),
               //     color = MaterialTheme.colorScheme.background
                //) {

                    //mBlockBrawlViewModel.navController = navController
                  //  BlockBrawlNavHost(navController = navController, blockBrawlViewModel = mBlockBrawlViewModel, context = context)
                //}
            //}
        }
    }
}

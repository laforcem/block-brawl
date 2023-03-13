package edu.mines.csci448.pcm.blockbrawl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import edu.mines.csci448.pcm.blockbrawl.presentation.navigation.BlockBrawlNavHost
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.BlockBrawlViewModel
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.BlockBrawlViewModelFactory
import edu.mines.csci448.pcm.blockbrawl.ui.theme.BlockBrawlTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //create factory & view model
        val factory = BlockBrawlViewModelFactory()
        val mBlockBrawlViewModel = BlockBrawlViewModel() //TODO: have factory create view model

        setContent {
            BlockBrawlTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val context = LocalContext.current
                    mBlockBrawlViewModel.navController = navController
                    BlockBrawlNavHost(navController = navController, blockBrawlViewModel = mBlockBrawlViewModel, context = context)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BlockBrawlTheme {
        Greeting("Android")
    }
}
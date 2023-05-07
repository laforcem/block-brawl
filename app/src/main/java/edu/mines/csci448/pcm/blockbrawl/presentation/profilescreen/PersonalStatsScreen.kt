package edu.mines.csci448.pcm.blockbrawl.presentation.profilescreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.mines.csci448.pcm.blockbrawl.R
import edu.mines.csci448.pcm.blockbrawl.data.BlockBrawlRepo
import edu.mines.csci448.pcm.blockbrawl.presentation.settings.SettingsScreen
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.BlockBrawlViewModel
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.IBlockBrawlViewModel
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.PreviewBlockBrawlViewModel

@Composable
fun PersonalStatsScreen(
    blockBrawlViewModel: IBlockBrawlViewModel,
    onBackClicked: () -> Unit
){
        //List of Level Leaderboards Should go Here Should implement lazy column later
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ){
            Card(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Text(text = "Test Stats Card for each Level")
            }
        }
    }

@Preview(showBackground = true)
@Composable
private fun PreviewPersonalStatsScreen(){
    PersonalStatsScreen(PreviewBlockBrawlViewModel()) {}
}

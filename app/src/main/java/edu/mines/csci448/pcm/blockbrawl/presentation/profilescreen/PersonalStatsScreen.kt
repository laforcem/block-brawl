package edu.mines.csci448.pcm.blockbrawl.presentation.profilescreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.mines.csci448.pcm.blockbrawl.R
import edu.mines.csci448.pcm.blockbrawl.data.BlockBrawlLevel
import edu.mines.csci448.pcm.blockbrawl.data.BlockBrawlRepo
import edu.mines.csci448.pcm.blockbrawl.data.NumberOfLevels
import edu.mines.csci448.pcm.blockbrawl.presentation.settings.SettingsScreen
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.BlockBrawlViewModel
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.IBlockBrawlViewModel
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.PreviewBlockBrawlViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalStatsScreen(
    blockBrawlViewModel: IBlockBrawlViewModel,
    onLevelStatsClicked: (levelNumber: Int) -> Unit,
    onBackClicked: () -> Unit
) {
    //List of players best stats for each level
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        //List of Levels in the game
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(NumberOfLevels.listOfLevelNumbers) { level ->
                Card(
                    onClick = { onLevelStatsClicked(level) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Block Brawl Level: ${level}",
                            fontSize = 25.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPersonalStatsScreen() {
    PersonalStatsScreen(PreviewBlockBrawlViewModel(), {}) {}
}
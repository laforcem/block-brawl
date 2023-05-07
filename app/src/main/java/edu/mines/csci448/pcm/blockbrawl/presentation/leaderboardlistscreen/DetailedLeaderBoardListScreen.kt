package edu.mines.csci448.pcm.blockbrawl.presentation.leaderboardlistscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.mines.csci448.pcm.blockbrawl.R
import edu.mines.csci448.pcm.blockbrawl.data.BlockBrawlLevel
import edu.mines.csci448.pcm.blockbrawl.data.NumberOfLevels
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.IBlockBrawlViewModel
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.PreviewBlockBrawlViewModel

@Composable
fun DetailedLeaderboardListScreen(
    blockBrawlViewModel: IBlockBrawlViewModel,
    onBackClicked: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(blockBrawlViewModel.levelListState.value) { level ->
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = "Level Number: ${level.levelNumber}\nScore: ${level.score}\nUser: ${level.userName}\nCompleted In Time: ${level.completed}\nDate Played: ${level.date}",
                            fontSize = 25.sp,
                            textAlign = TextAlign.Center,
                            lineHeight = 30.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailedLeaderboardListScreen(){
    DetailedLeaderboardListScreen(
        blockBrawlViewModel = PreviewBlockBrawlViewModel(),
        onBackClicked = {}
    )
}
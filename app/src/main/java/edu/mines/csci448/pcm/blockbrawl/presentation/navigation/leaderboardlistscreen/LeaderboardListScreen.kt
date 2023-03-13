package edu.mines.csci448.pcm.blockbrawl.presentation.navigation.leaderboardlistscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
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
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.BlockBrawlViewModel

@Composable
fun LeaderboardListScreen(
                        blockBrawlViewModel: BlockBrawlViewModel,
                        onBackClicked:() -> Unit,
                        onLeaderBoardItemClicked: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ){
        //Pause Icon Button
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth(.7f)
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                IconButton(
                    onClick = { onBackClicked() },
                    modifier = Modifier
                        .size(55.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button_desc),
                        modifier = Modifier
                            .padding(12.dp)
                            .size(80.dp)
                    )
                }
                Text(
                    text = stringResource(id = R.string.leaderboard_screen_title),
                    fontSize = 30.sp,
                    lineHeight = 30.sp
                )
            }
        }

        //List of Level Leaderboards Should go Here Should implement lazy column later
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ){
            Button(
                onClick = { onLeaderBoardItemClicked() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
                ){
                Text(text = "Test Leaderboard Level Item")
            }
        }
    }
}

@Preview
@Composable
private fun PreviewLeaderboardListScreen(){
    LeaderboardListScreen(blockBrawlViewModel = BlockBrawlViewModel(), {}, {})
}
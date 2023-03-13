package edu.mines.csci448.pcm.blockbrawl.presentation.navigation.leaderboardlistscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.mines.csci448.pcm.blockbrawl.R
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.BlockBrawlViewModel

@Composable
fun DetailedLeaderboardListScreen(
                                blockBrawlViewModel: BlockBrawlViewModel,
                                onBackClicked: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ){
        //Pause Icon Button
        Box(
            contentAlignment = Alignment.TopStart,
            modifier = Modifier
                .fillMaxWidth()
        ){
            IconButton(
                onClick = { onBackClicked() },
                modifier = Modifier
                    .size(55.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.game_pause_desc),
                    modifier = Modifier
                        .padding(12.dp)
                        .size(80.dp)
                )
            }
        }

        //Game Content Would go here
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ){
            Text(
                text = "Leaderboard content would go here",
                fontSize = 50.sp,
                textAlign = TextAlign.Center,
                lineHeight = 50.sp
            )
        }
    }
}
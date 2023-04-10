package edu.mines.csci448.pcm.blockbrawl.presentation.gamescreen

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.mines.csci448.pcm.blockbrawl.R
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.BlockBrawlViewModel

@Composable
fun GameScreen(
            blockBrawlViewModel: BlockBrawlViewModel,
            onPauseClicked: () -> Unit
)
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ){

        //Game Content Would go here
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ){
            Text(
                text = "Game Screen Content Would Go Here",
                fontSize = 50.sp,
                textAlign = TextAlign.Center,
                lineHeight = 50.sp
            )
        }
    }
}

@Preview
@Composable
fun GameScreenPreview(){
    GameScreen(blockBrawlViewModel = BlockBrawlViewModel(), {})
}
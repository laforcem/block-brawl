package edu.mines.csci448.pcm.blockbrawl.presentation.leaderboardlistscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.IBlockBrawlViewModel
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.PreviewBlockBrawlViewModel

@Composable
fun LeaderboardListScreen(
    blockBrawlViewModel: IBlockBrawlViewModel,
    onBackClicked: () -> Unit,
    onLeaderBoardItemClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        //List of Level Leaderboards Should go Here Should implement lazy column later
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Button(
                onClick = { onLeaderBoardItemClicked() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
            ) {
                Text(text = "Test Leaderboard Level Item")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewLeaderboardListScreen() {
    LeaderboardListScreen(blockBrawlViewModel = PreviewBlockBrawlViewModel(), {}, {})
}
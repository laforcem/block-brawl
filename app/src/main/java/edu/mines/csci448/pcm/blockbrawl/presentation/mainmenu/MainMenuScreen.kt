package edu.mines.csci448.pcm.blockbrawl.presentation.mainmenu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import edu.mines.csci448.pcm.blockbrawl.R
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.BlockBrawlViewModel
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.IBlockBrawlViewModel
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.PreviewBlockBrawlViewModel


@Composable
fun MainMenuScreen(
    blockBrawlViewModel: IBlockBrawlViewModel,
    onPlayClicked: () -> Unit,
    onLeaderboardClicked: () -> Unit,
    onSettingsClicked: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        //Title text
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .wrapContentHeight(Alignment.CenterVertically)
                .fillMaxSize()
                .weight(0.6f)
        ) {
            Text(
                text = stringResource(R.string.app_title),
                style = TextStyle(fontWeight = FontWeight.Bold),
                fontSize = 70.sp,
                lineHeight = 1.2.em,
                modifier = Modifier
            )
        }

        //Menu buttons
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.7f)
        ) {
            Column(
                modifier = Modifier
                    .width(250.dp)
                    .padding(top = 40.dp)
            ) {

                //Play button
                Button(
                    onClick = { onPlayClicked() },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.menu_button_play),
                        fontSize = 22.sp,
                        modifier = Modifier
                    )
                }

                //Leaderboard button
                Button(
                    onClick = { onLeaderboardClicked() },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.menu_button_leaderboard),
                        fontSize = 22.sp,
                        modifier = Modifier
                    )
                }

                //Settings button
                Button(
                    onClick = { onSettingsClicked() },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.menu_button_settings),
                        fontSize = 22.sp,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainMenuScreenPreview() {
    MainMenuScreen(
        blockBrawlViewModel = PreviewBlockBrawlViewModel(),
        onPlayClicked = {},
        onLeaderboardClicked = {},
        onSettingsClicked = {})
}
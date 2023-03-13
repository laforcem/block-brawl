package edu.mines.csci448.pcm.blockbrawl.presentation.navigation.mainmenu

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import edu.mines.csci448.pcm.blockbrawl.R
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
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.BlockBrawlViewModel

@Composable
fun MainMenuScreen(
    blockBrawlViewModel: BlockBrawlViewModel,
    onProfileClicked: () -> Unit,
    onPlayClicked: () -> Unit,
    onLeaderboardClicked: () -> Unit,
    onSettingsClicked: () -> Unit
){
    Column(modifier = Modifier.fillMaxSize()) {

        //Profile icon button
        Box(contentAlignment = Alignment.TopEnd, modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = { onProfileClicked() }, modifier = Modifier.size(80.dp)) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = stringResource(R.string.menu_profile_desc),
                    modifier = Modifier.padding(12.dp).size(80.dp)
                )
            }
        }

        //Title text
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.wrapContentHeight(Alignment.CenterVertically).fillMaxSize()
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
            modifier = Modifier.fillMaxWidth().weight(0.7f)
        ) {
            Column(modifier = Modifier.width(250.dp).padding(top = 40.dp)) {

                //Play button
                Button(
                    onClick = { onPlayClicked() },
                    modifier = Modifier.padding(8.dp).fillMaxWidth()
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
                    modifier = Modifier.padding(8.dp).fillMaxWidth()
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
                    modifier = Modifier.padding(8.dp).fillMaxWidth()
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

@Preview
@Composable
fun MainMenuScreenPreview(){
    MainMenuScreen(BlockBrawlViewModel(), {}, {}, {}, {})
}

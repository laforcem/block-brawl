package edu.mines.csci448.pcm.blockbrawl.presentation.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import edu.mines.csci448.pcm.blockbrawl.R
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.IBlockBrawlViewModel

@Composable
fun SettingsScreen(blockBrawlViewModel: IBlockBrawlViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {

        //Title Text
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .wrapContentHeight(Alignment.CenterVertically)
                .fillMaxSize()
                .weight(0.15f)
        ) {
            Text(
                text = stringResource(R.string.menu_button_settings),
                style = TextStyle(fontWeight = FontWeight.Bold),
                fontSize = 45.sp,
                lineHeight = 1.2.em,
                modifier = Modifier
            )
        }

        //Settings
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.85f)
        ) {
            Column() {

                //Sound effects
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(20.dp)
                ) {
                    Checkbox(
                        checked = blockBrawlViewModel.soundFxState.collectAsState().value,
                        onCheckedChange = { blockBrawlViewModel.setSoundFxState(it) },
                        modifier = Modifier.scale(1.5f)
                    )
                    Box(
                        contentAlignment = Alignment.CenterStart, modifier = Modifier
                            .wrapContentHeight(Alignment.CenterVertically)
                            .fillMaxHeight()
                    ) {
                        Text(text = stringResource(R.string.setting_text_1), fontSize = 30.sp)
                    }
                }

                //Music
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(20.dp)
                ) {
                    Checkbox(
                        checked = blockBrawlViewModel.musicState.collectAsState().value,
                        onCheckedChange = { blockBrawlViewModel.setMusicState(it) },
                        modifier = Modifier.scale(1.5f)
                    )
                    Box(
                        contentAlignment = Alignment.CenterStart, modifier = Modifier
                            .wrapContentHeight(Alignment.CenterVertically)
                            .fillMaxHeight()
                    ) {
                        Text(text = stringResource(R.string.setting_text_2), fontSize = 30.sp)
                    }
                }
            }
        }
    }
}

/*
@Preview
@Composable
fun SettingsScreenPreview(){
    SettingsScreen(BlockBrawlViewModel())
}*/

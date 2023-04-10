package edu.mines.csci448.pcm.blockbrawl.presentation.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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

@Composable
fun SettingsScreen(blockBrawlViewModel: BlockBrawlViewModel){
    Column(modifier = Modifier.fillMaxSize()){

        //Settings
        Box(
            modifier = Modifier.fillMaxSize().weight(0.85f)
        ){
            Column(){

                //Sound effects
                Row(modifier = Modifier.fillMaxWidth().height(80.dp).padding(20.dp)){
                    Box(contentAlignment = Alignment.CenterStart, modifier = Modifier.wrapContentHeight(Alignment.CenterVertically).fillMaxHeight()){
                        Text(text = stringResource(R.string.setting_text_1), fontSize = 30.sp)
                    }
                    IconButton(onClick = {  }, modifier = Modifier.size(80.dp)) {
                        Icon(
                            imageVector = Icons.Filled.CheckBoxOutlineBlank,
                            contentDescription = stringResource(R.string.setting_text_1),
                            modifier = Modifier.size(50.dp)
                        )
                    }
                }

                //Music
                Row(modifier = Modifier.fillMaxWidth().height(80.dp).padding(20.dp)){
                    Box(contentAlignment = Alignment.CenterStart, modifier = Modifier.wrapContentHeight(Alignment.CenterVertically).fillMaxHeight()){
                        Text(text = stringResource(R.string.setting_text_2), fontSize = 30.sp)
                    }
                    IconButton(onClick = {  }, modifier = Modifier.size(80.dp)) {
                        Icon(
                            imageVector = Icons.Filled.CheckBox,
                            contentDescription = stringResource(R.string.setting_text_2),
                            modifier = Modifier.size(50.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview(){
    SettingsScreen(BlockBrawlViewModel())
}
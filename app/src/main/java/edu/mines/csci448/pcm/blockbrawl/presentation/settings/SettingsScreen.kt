package edu.mines.csci448.pcm.blockbrawl.presentation.settings

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import edu.mines.csci448.pcm.blockbrawl.R
import edu.mines.csci448.pcm.blockbrawl.data.BlockBrawlRepo
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.BlockBrawlViewModel
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.IBlockBrawlViewModel
import edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel.PreviewBlockBrawlViewModel
import edu.mines.olsgard_a4.util.DataStoreManager
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(context: Context) {

    val dataStoreManager = remember { DataStoreManager(context) }
    val lifecycleOwner = LocalLifecycleOwner.current
    val coroutineScope = rememberCoroutineScope()
    val musicState = dataStoreManager
        .musicDataflow
        .collectAsStateWithLifecycle(
            initialValue = true,
            lifecycle = lifecycleOwner.lifecycle
        )
    val sfxState = dataStoreManager
        .sfxDataflow
        .collectAsStateWithLifecycle(
            initialValue = true,
            lifecycle = lifecycleOwner.lifecycle
        )

    Column(modifier = Modifier.fillMaxSize()) {
        //Settings
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.85f)
        ) {
            Column {
                //Sound effects
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Switch(
                        checked = sfxState.value,
                        onCheckedChange ={
                            coroutineScope.launch {
                                dataStoreManager.setSfx(it)
                            }
                        },
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
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Switch(
                        checked = musicState.value,
                        onCheckedChange ={
                            coroutineScope.launch {
                                dataStoreManager.setMusic(it)
                            }
                        },                        modifier = Modifier.scale(1.5f)
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

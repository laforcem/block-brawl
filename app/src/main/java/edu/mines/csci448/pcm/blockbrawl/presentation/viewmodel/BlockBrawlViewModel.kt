package edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import edu.mines.csci448.pcm.blockbrawl.data.BlockBrawlRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BlockBrawlViewModel(private val blockBrawlRepo: BlockBrawlRepo) : ViewModel()  {
    lateinit var navController: NavHostController

    private val mTitleTextState: MutableStateFlow<String>
        = MutableStateFlow("")
    val titleTextState: StateFlow<String>
        get() = mTitleTextState.asStateFlow()


    fun changeTitleText(title: String?){
        if (title == null){
            mTitleTextState.value = ""
        }
        else{
            mTitleTextState.value = title;
        }
    }
}
package edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BlockBrawlViewModel : ViewModel(), IBlockBrawlViewModel  {
    companion object {
        private const val LOG_TAG = "pcm.BlockBrawlViewModel"
    }

    lateinit var navController: NavHostController

    private val mSoundFxState = MutableStateFlow(true)
    override val soundFxState: StateFlow<Boolean>
        get() = mSoundFxState.asStateFlow()

    private val mMusicState = MutableStateFlow(true)
    override val musicState: StateFlow<Boolean>
        get() = mMusicState.asStateFlow()

    override fun setSoundFxState(state: Boolean) {
        Log.d(LOG_TAG, "setSoundFxState($state)")
        mSoundFxState.value = state
    }

    override fun setMusicState(state: Boolean) {
        Log.d(LOG_TAG, "setMusicState($state)")
        mMusicState.value = state
    }
}
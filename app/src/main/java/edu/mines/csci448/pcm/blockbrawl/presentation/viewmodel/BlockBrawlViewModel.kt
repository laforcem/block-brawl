package edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import edu.mines.csci448.pcm.blockbrawl.data.BlockBrawlRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BlockBrawlViewModel(private val blockBrawlRepo: BlockBrawlRepo) : ViewModel(),
    IBlockBrawlViewModel {
    companion object {
        private const val LOG_TAG = "pcm.BlockBrawlViewModel"
    }

    lateinit var navController: NavHostController

    private val mTitleTextState: MutableStateFlow<String> = MutableStateFlow("")
    override val titleTextState: StateFlow<String>
        get() = mTitleTextState.asStateFlow()


    fun changeTitleText(title: String?) {
        Log.d(LOG_TAG, "changeTitleText($title)")
        if (title == null) {
            mTitleTextState.value = ""
        } else {
            mTitleTextState.value = title;
        }
    }

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
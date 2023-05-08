package edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel

import android.util.Log
import androidx.navigation.NavHostController
import edu.mines.csci448.pcm.blockbrawl.data.BlockBrawlLevel
import edu.mines.csci448.pcm.blockbrawl.presentation.gamescreen.Block
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

class PreviewBlockBrawlViewModel() :
    IBlockBrawlViewModel {

    companion object {
        private const val LOG_TAG = "pcm.BlockBrawlViewModel"
    }

    lateinit var navController: NavHostController

    private val mTitleTextState: MutableStateFlow<String> = MutableStateFlow("")
    override val titleTextState: StateFlow<String>
        get() = mTitleTextState.asStateFlow()


    override fun changeTitleText(title: String?) {
        Log.d(LOG_TAG, "changeTitleText($title)")
        if (title == null) {
            mTitleTextState.value = ""
        } else {
            mTitleTextState.value = title
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

    private val mLevels: MutableStateFlow<List<BlockBrawlLevel>> = MutableStateFlow(emptyList())

    override val levelListState: StateFlow<List<BlockBrawlLevel>>
        get() = mLevels.asStateFlow()

    private val mCurrentLevelIdState: MutableStateFlow<UUID> =
        MutableStateFlow(UUID.randomUUID())

    private val mCurrentLevelState: MutableStateFlow<BlockBrawlLevel?> = MutableStateFlow(null)

    override val currentLevelState: StateFlow<BlockBrawlLevel?>
        get() = mCurrentLevelState.asStateFlow()

    private val mUsername = MutableStateFlow("Malcolm")
    override val username: StateFlow<String>
        get() = mUsername.asStateFlow()

    override fun loadLevelById(uuid: UUID) {
        mCurrentLevelIdState.update { uuid }
        Log.d(LOG_TAG, "Level not found")
    }

    override fun addLevelStats(blockBrawlLevel: BlockBrawlLevel) {
        Log.d(LOG_TAG, "adding level stats $blockBrawlLevel")
    }

    override fun deleteLevelStats(blockBrawlLevel: BlockBrawlLevel) {
        Log.d(LOG_TAG, "deleting level stats $blockBrawlLevel")
    }

    override fun setUsername(username: String) {
        Log.d(LOG_TAG, "setUsername($username)")
        mUsername.value = username
    }

    override fun getStatsByLevelNumber(levelNumber: Int) {

    }

    override fun getBestLevelStats(levelNumber: Int) {

    }

    override fun getBlockList(levelNumber: Int): List<Block> {
        return emptyList()
    }
}
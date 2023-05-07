package edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import edu.mines.csci448.pcm.blockbrawl.data.BlockBrawlLevel
import edu.mines.csci448.pcm.blockbrawl.data.BlockBrawlRepo
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

class BlockBrawlViewModel(private val blockBrawlRepo: BlockBrawlRepo) : ViewModel(),
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

    private val mUsername = MutableStateFlow("Player")
    override val username: StateFlow<String>
        get() = mUsername.asStateFlow()

    override fun setMusicState(state: Boolean) {
        Log.d(LOG_TAG, "setMusicState($state)")
        mMusicState.value = state
    }

    private val mLevels: MutableStateFlow<List<BlockBrawlLevel>> = MutableStateFlow(emptyList())

    override val levelListState: StateFlow<List<BlockBrawlLevel>>
        get() = mLevels.asStateFlow()

    private val mCurrentLevelIdState: MutableStateFlow<UUID> = MutableStateFlow(UUID.randomUUID())

    private val mCurrentLevelState: MutableStateFlow<BlockBrawlLevel?> = MutableStateFlow(null)

    override val currentLevelState: StateFlow<BlockBrawlLevel?>
        get() = mCurrentLevelState.asStateFlow()

    init {
        viewModelScope.launch {
            blockBrawlRepo.getLevelStats().collect{ levelList ->
                mLevels.update { levelList }
            }
        }
        viewModelScope.launch {
            mCurrentLevelIdState
                .map { uuid -> blockBrawlRepo.getStatsByLevelId(uuid) }
                .collect { level -> mCurrentLevelState.update { level } }
        }
    }

    override fun loadLevelById(uuid: UUID) {
        mCurrentLevelIdState.update { uuid }
        Log.d(LOG_TAG, "Level not found")
    }

    override fun addLevelStats(blockBrawlLevel: BlockBrawlLevel) {
        Log.d(LOG_TAG, "adding level stats $blockBrawlLevel")
        blockBrawlRepo.addLevelStats(blockBrawlLevel)
    }

    override fun deleteLevelStats(blockBrawlLevel: BlockBrawlLevel) {
        Log.d(LOG_TAG, "deleting level stats $blockBrawlLevel")
        blockBrawlRepo.deleteLevel(blockBrawlLevel)
    }

    override fun setUsername(username: String) {
        Log.d(LOG_TAG, "setUsername($username)")
        mUsername.value = username
    }
}
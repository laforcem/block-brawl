package edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel

import edu.mines.csci448.pcm.blockbrawl.data.BlockBrawlLevel
import kotlinx.coroutines.flow.StateFlow
import java.util.*

interface IBlockBrawlViewModel {
    // TODO: Refactor project to use this interface
    val soundFxState: StateFlow<Boolean>
    val musicState: StateFlow<Boolean>
    val titleTextState: StateFlow<String>
    val levelListState: StateFlow<List<BlockBrawlLevel>>
    val currentLevelState: StateFlow<BlockBrawlLevel?>
    val username: StateFlow<String>

    fun loadLevelById(uuid: UUID)
    fun addLevelStats(blockBrawlLevel: BlockBrawlLevel)
    fun deleteLevelStats(blockBrawlLevel: BlockBrawlLevel)
    fun setSoundFxState(state: Boolean)
    fun setMusicState(state: Boolean)
    fun changeTitleText(title: String?)
    fun setUsername(username: String)
}
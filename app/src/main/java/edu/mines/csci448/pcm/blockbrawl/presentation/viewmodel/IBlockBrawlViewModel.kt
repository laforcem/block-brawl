package edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel

import kotlinx.coroutines.flow.StateFlow

interface IBlockBrawlViewModel {
    // TODO: Refactor project to use this interface
    val soundFxState: StateFlow<Boolean>
    val musicState: StateFlow<Boolean>
    fun setSoundFxState(state: Boolean)
    fun setMusicState(state: Boolean)
}
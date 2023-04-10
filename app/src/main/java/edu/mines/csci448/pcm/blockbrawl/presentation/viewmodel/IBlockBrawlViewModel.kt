package edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel

import kotlinx.coroutines.flow.StateFlow

interface IBlockBrawlViewModel {
    val soundFxState: StateFlow<Boolean>
    val musicState: StateFlow<Boolean>
    val titleTextState: StateFlow<String>
    fun setSoundFxState(state: Boolean)
    fun setMusicState(state: Boolean)
}
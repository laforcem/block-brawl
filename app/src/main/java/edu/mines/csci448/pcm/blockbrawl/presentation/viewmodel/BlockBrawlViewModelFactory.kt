package edu.mines.csci448.pcm.blockbrawl.presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.mines.csci448.pcm.blockbrawl.data.BlockBrawlRepo

class BlockBrawlViewModelFactory (private val context: Context) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        private const val LOG_TAG = "448.BlockBrawlViewModelFactory"
    }

    fun getViewModelClass() = BlockBrawlViewModel::class.java

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        Log.d(LOG_TAG, "create() called")
        if(modelClass.isAssignableFrom(getViewModelClass())) {
            Log.d(LOG_TAG, "creating ViewModel: ${getViewModelClass()}")
            return modelClass
                .getConstructor(BlockBrawlRepo::class.java)
                .newInstance(BlockBrawlRepo.getInstance(context))
        }
        Log.e(LOG_TAG, "Unknown ViewModel: $modelClass")
        throw IllegalArgumentException("Unknown ViewModel")
    }
}
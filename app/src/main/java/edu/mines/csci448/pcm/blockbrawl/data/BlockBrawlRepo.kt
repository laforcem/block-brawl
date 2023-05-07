package edu.mines.csci448.pcm.blockbrawl.data

import android.content.Context
import edu.mines.csci448.pcm.blockbrawl.data.database.BlockBrawlDao
import edu.mines.csci448.pcm.blockbrawl.data.database.BlockBrawlDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.*

class BlockBrawlRepo @OptIn(DelicateCoroutinesApi::class)
private constructor(private val blockBrawlDao: BlockBrawlDao, private val coroutineScope: CoroutineScope = GlobalScope) {
    companion object {
        private const val LOG_TAG = "448.BlockBrawl"
        @Volatile private var INSTANCE: BlockBrawlRepo? = null

        fun getInstance(context: Context): BlockBrawlRepo {
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null) {
                    val database = BlockBrawlDatabase.getInstance(context)
                    instance = BlockBrawlRepo(database.blockBrawlDao)
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    fun addLevelStats(level: BlockBrawlLevel) {
        coroutineScope.launch {
            blockBrawlDao.addLevelStats(level)
        }
    }
    fun getLevelStats(): Flow<List<BlockBrawlLevel>> = blockBrawlDao.getLevelStats()
    suspend fun getStatsByLevelId(id: UUID): BlockBrawlLevel? = blockBrawlDao.getStatsByLevelId(id)
    suspend fun getStatsByLevelNumber(levelNumber: Int):  Flow<List<BlockBrawlLevel>> = blockBrawlDao.getStatsByLevelNumber(levelNumber)
    suspend fun getBestLevelStats(userName: String, levelNumber: Int):  Flow<List<BlockBrawlLevel>> = blockBrawlDao.getBestLevelStats(userName, levelNumber)
    fun deleteLevel(level: BlockBrawlLevel) {
        coroutineScope.launch {
            blockBrawlDao.deleteLevel(level)
        }
    }
}
package edu.mines.csci448.pcm.blockbrawl.data

import android.content.Context
import edu.mines.csci448.pcm.blockbrawl.data.database.BlockBrawlDao
import edu.mines.csci448.pcm.blockbrawl.data.database.BlockBrawlDatabase
import edu.mines.csci448.pcm.blockbrawl.presentation.gamescreen.Block
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

    // Blocks for level 1
    val block1_1 = Block(
        3, 0, true,
        arrayOf(
            arrayOf('C'),
            arrayOf('X')
        ),
        5
    )
    val block1_2 = Block(
        1, 0, true,
        arrayOf(
            arrayOf('X', 'C'),
            arrayOf(' ', 'X')
        ),
        20
    )
    val block1_3 = Block(
        1, 1, false,
        arrayOf(
            arrayOf('X', 'X', ' ', ' '),
            arrayOf(' ', 'C', 'X', ' '),
            arrayOf(' ', ' ', 'X', 'X')
        ),
        30
    )
    val block1_4 = Block(
        2, 2, true,
        arrayOf(
            arrayOf(' ', 'X', ' '),
            arrayOf(' ', 'X', ' '),
            arrayOf('X', 'C', ' '),
            arrayOf('X', ' ', ' ')
        ),
        20
    )
    val block1_5 = Block(
        4, 1, false,
        arrayOf(
            arrayOf('C', ' '),
            arrayOf('X', 'X'),
        ),
        10
    )
    val block1_6 = Block(
        5, 1, true,
        arrayOf(
            arrayOf(' ', 'X'),
            arrayOf('X', 'C')
        ),
        15
    )
    val block1_7 = Block(
        4, 2, true,
        arrayOf(
            arrayOf('X', 'C', 'X'),
            arrayOf('X', ' ', ' ')
        ),
        15
    )

    val block1_8 = Block(
        4, 0, false,
        arrayOf(
            arrayOf('C', 'X'),
        ),
        5
    )




    val blocksLevel1: List<Block> = listOf(block1_1, block1_2, block1_3, block1_4, block1_5, block1_6, block1_7, block1_8)
    val blocksLevel2: List<Block> = emptyList()
    val blocksLevel3: List<Block> = emptyList()
    val blocksLevel4: List<Block> = emptyList()
    val blocksLevel5: List<Block> = emptyList()
    val blocksLevel6: List<Block> = emptyList()

    val levelBlocks: Array<List<Block>> = arrayOf(blocksLevel1, blocksLevel2, blocksLevel3, blocksLevel4, blocksLevel5, blocksLevel6)




}
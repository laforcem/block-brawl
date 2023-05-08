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
private constructor(
    private val blockBrawlDao: BlockBrawlDao,
    private val coroutineScope: CoroutineScope = GlobalScope
) {
    companion object {
        private const val LOG_TAG = "448.BlockBrawl"

        @Volatile
        private var INSTANCE: BlockBrawlRepo? = null

        fun getInstance(context: Context): BlockBrawlRepo {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
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
    suspend fun getStatsByLevelNumber(levelNumber: Int): Flow<List<BlockBrawlLevel>> =
        blockBrawlDao.getStatsByLevelNumber(levelNumber)

    suspend fun getBestLevelStats(userName: String, levelNumber: Int): Flow<List<BlockBrawlLevel>> =
        blockBrawlDao.getBestLevelStats(userName, levelNumber)

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

    ////////////////////////////////////////////////////////////////

    // Blocks for level 2
    val block2_1 = Block(
        3, 2, true,
        arrayOf(
            arrayOf('X'),
            arrayOf('X'),
            arrayOf('C'),
            arrayOf('X')
        ),
        5
    )
    val block2_2 = Block(
        1, 0, true,
        arrayOf(
            arrayOf('X', 'C'),
            arrayOf('X', 'X')
        ),
        20
    )
    val block2_3 = Block(
        3, 1, false,
        arrayOf(
            arrayOf('X', 'X', 'X', 'X'),
            arrayOf(' ', ' ', 'C', 'X'),
            arrayOf(' ', ' ', ' ', 'X')
        ),
        30
    )
    val block2_4 = Block(
        2, 1, true,
        arrayOf(
            arrayOf('X'),
            arrayOf('C')
        ),
        20
    )
    val block2_5 = Block(
        1, 2, true,
        arrayOf(
            arrayOf(' ', 'C', ' '),
            arrayOf('X', 'X', 'X'),
        ),
        10
    )
    val block2_6 = Block(
        5, 1, true,
        arrayOf(
            arrayOf('X', ' '),
            arrayOf('X', 'C')
        ),
        15
    )

    ////////////////////////////////////////////////////////////////

    // Blocks for level 3
    val block3_1 = Block(
        4, 1, false,
        arrayOf(
            arrayOf('C')
        ),
        5
    )
    val block3_2 = Block(
        0, 2, true,
        arrayOf(
            arrayOf('X', ' '),
            arrayOf('X', ' '),
            arrayOf('C', 'X'),
            arrayOf(' ', 'X')
        ),
        20
    )
    val block3_3 = Block(
        3, 3, false,
        arrayOf(
            arrayOf('X', ' '),
            arrayOf('X', ' '),
            arrayOf('X', ' '),
            arrayOf('C', 'X')
        ),
        30
    )
    val block3_4 = Block(
        2, 1, true,
        arrayOf(
            arrayOf('X', ' '),
            arrayOf('C', 'X')
        ),
        5
    )
    val block3_5 = Block(
        0, 3, false,
        arrayOf(
            arrayOf('X', ' ', ' '),
            arrayOf('C', 'X', 'X'),
        ),
        15
    )
    val block3_6 = Block(
        1, 1, false,
        arrayOf(
            arrayOf('X', 'X', ' '),
            arrayOf(' ', 'C', 'X')
        ),
        10
    )
    val block3_7 = Block(
        5, 2, true,
        arrayOf(
            arrayOf(' ', ' ', 'X'),
            arrayOf(' ', ' ', 'X'),
            arrayOf('X', 'X', 'C'),
            arrayOf(' ', ' ', 'X')
        ),
        35
    )

    ////////////////////////////////////////////////////////////////

    // Blocks for level 4
    val block4_1 = Block(
        5, 1, false,
        arrayOf(
            arrayOf('C'),
            arrayOf('X')
        ),
        5
    )
    val block4_2 = Block(
        3, 0, false,
        arrayOf(
            arrayOf('C'),
            arrayOf('X')
        ),
        20
    )
    val block4_3 = Block(
        3, 2, false,
        arrayOf(
            arrayOf(' ', ' ', 'X'),
            arrayOf('X', 'C', 'X'),
        ),
        30
    )
    val block4_4 = Block(
        2, 1, true,
        arrayOf(
            arrayOf('X', 'X'),
            arrayOf('C', 'X')
        ),
        5
    )
    val block4_5 = Block(
        0, 1, false,
        arrayOf(
            arrayOf('X'),
            arrayOf('C'),
            arrayOf('X')
        ),
        10
    )
    val block4_6 = Block(
        2, 0, false,
        arrayOf(
            arrayOf('X', 'C')
        ),
        5
    )
    val block4_7 = Block(
        3, 3, true,
        arrayOf(
            arrayOf('X', 'X', 'X'),
            arrayOf('X', 'C', 'X')
        ),
        25
    )
    val block4_8 = Block(
        1, 1, true,
        arrayOf(
            arrayOf('X', 'X'),
            arrayOf(' ', 'C'),
            arrayOf(' ', 'X'),
            arrayOf(' ', 'X')
        ),
        30
    )

    ////////////////////////////////////////////////////////////////

    // Blocks for level 5
    val block5_1 = Block(
        5, 1, false,
        arrayOf(
            arrayOf('C'),
            arrayOf('X')
        ),
        10
    )
    val block5_2 = Block(
        4, 1, true,
        arrayOf(
            arrayOf('C')
        ),
        5
    )
    val block5_3 = Block(
        2, 1, false,
        arrayOf(
            arrayOf('X', 'C', 'X'),
            arrayOf(' ', 'X', 'X'),
        ),
        20
    )
    val block5_4 = Block(
        2, 1, true,
        arrayOf(
            arrayOf('X', 'X'),
            arrayOf('C', 'X')
        ),
        15
    )
    val block5_5 = Block(
        0, 1, false,
        arrayOf(
            arrayOf('C'),
            arrayOf('X')
        ),
        10
    )
    val block5_6 = Block(
        2, 0, false,
        arrayOf(
            arrayOf('X', 'X', 'C', 'X', 'X', 'X')
        ),
        25
    )
    val block5_7 = Block(
        1, 3, true,
        arrayOf(
            arrayOf('X', ' ', ' ', ' ', ' '),
            arrayOf('X', 'X', ' ', ' ', ' '),
            arrayOf('X', 'C', 'X', 'X', 'X')
        ),
        35
    )

    ////////////////////////////////////////////////////////////////

    // Blocks for level 6
    val block6_1 = Block(
        1, 0, false,
        arrayOf(
            arrayOf('C')
        ),
        10
    )
    val block6_2 = Block(
        1, 1, false,
        arrayOf(
            arrayOf('C')
        ),
        5
    )
    val block6_3 = Block(
        5, 1, false,
        arrayOf(
            arrayOf('X', 'X'),
            arrayOf('X', 'C'),
            arrayOf('X', 'X')
        ),
        25
    )
    val block6_4 = Block(
        1, 2, true,
        arrayOf(
            arrayOf('X', 'C', 'X')
        ),
        15
    )
    val block6_5 = Block(
        0, 2, false,
        arrayOf(
            arrayOf('C', 'X')
        ),
        10
    )
    val block6_6 = Block(
        3, 0, false,
        arrayOf(
            arrayOf('C'),
            arrayOf('X')
        ),
        15
    )
    val block6_7 = Block(
        3, 1, true,
        arrayOf(
            arrayOf('X', 'X', 'X', ' ', 'X'),
            arrayOf('X', ' ', 'C', ' ', 'X'),
            arrayOf(' ', ' ', 'X', 'X', 'X')
        ),
        35
    )
    val block6_8 = Block(
        2, 1, false,
        arrayOf(
            arrayOf('X'),
            arrayOf('C'),
            arrayOf('X')
        ),
        20
    )


    val blocksLevel1: List<Block> =
        listOf(block1_1, block1_2, block1_3, block1_4, block1_5, block1_6, block1_7, block1_8)
    val blocksLevel2: List<Block> =
        listOf(block2_1, block2_2, block2_3, block2_4, block2_5, block2_6)
    val blocksLevel3: List<Block> =
        listOf(block3_1, block3_2, block3_3, block3_4, block3_5, block3_6, block3_7)
    val blocksLevel4: List<Block> =
        listOf(block4_1, block4_2, block4_3, block4_4, block4_5, block4_6, block4_7, block4_8)
    val blocksLevel5: List<Block> =
        listOf(block5_1, block5_2, block5_3, block5_4, block5_5, block5_6, block5_7)
    val blocksLevel6: List<Block> =
        listOf(block6_1, block6_2, block6_3, block6_4, block6_5, block6_6, block6_7, block6_8)

    val levelBlocks: Array<List<Block>> =
        arrayOf(blocksLevel1, blocksLevel2, blocksLevel3, blocksLevel4, blocksLevel5, blocksLevel6)


}
package edu.mines.csci448.pcm.blockbrawl.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import edu.mines.csci448.pcm.blockbrawl.data.BlockBrawlLevel
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface BlockBrawlDao {
    @Insert
    fun addLevelStats(level: BlockBrawlLevel)
    @Query("SELECT * FROM levels")
    fun getLevelStats(): Flow<List<BlockBrawlLevel>>
    @Query("SELECT * FROM levels WHERE id=(:id)")
    suspend fun getStatsByLevelId(id: UUID): BlockBrawlLevel?
    @Delete
    suspend fun deleteLevel(level: BlockBrawlLevel)
}
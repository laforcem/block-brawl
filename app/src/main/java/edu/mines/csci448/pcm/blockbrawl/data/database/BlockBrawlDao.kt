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
    @Query("SELECT * FROM levels WHERE levelNumber=(:levelNumber)")
    fun getStatsByLevelNumber(levelNumber: Int):  Flow<List<BlockBrawlLevel>>
    @Query("SELECT MAX(score), * FROM levels WHERE userName LIKE (:userName) AND completed AND levelNumber=(:levelNumber)")
    fun getBestLevelStats(userName: String, levelNumber: Int):  Flow<List<BlockBrawlLevel>>
    @Delete
    suspend fun deleteLevel(level: BlockBrawlLevel)
}
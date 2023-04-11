package edu.mines.csci448.pcm.blockbrawl.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "levels")
data class BlockBrawlLevel(
                            @PrimaryKey
                            val id: UUID,
                            val levelNumber: Int,
                            val completion: Boolean,
                            val completionTime: Int,
                            )

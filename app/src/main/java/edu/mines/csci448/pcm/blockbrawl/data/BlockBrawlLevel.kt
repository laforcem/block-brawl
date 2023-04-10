package edu.mines.csci448.pcm.blockbrawl.data

import androidx.room.Entity
import java.util.*

@Entity(tableName = "levels")
data class BlockBrawlLevel(val levelId: UUID)

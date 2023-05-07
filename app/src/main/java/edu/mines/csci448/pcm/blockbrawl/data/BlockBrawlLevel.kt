package edu.mines.csci448.pcm.blockbrawl.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Entity(tableName = "levels")
data class BlockBrawlLevel(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val userName: String,
    val levelNumber: Int,
    val completed: Boolean,
    val score: Int,
    val date: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
)

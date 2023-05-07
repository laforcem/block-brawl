package edu.mines.csci448.pcm.blockbrawl.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class BlockBrawlUser(
    @PrimaryKey
    val userName: String,
)
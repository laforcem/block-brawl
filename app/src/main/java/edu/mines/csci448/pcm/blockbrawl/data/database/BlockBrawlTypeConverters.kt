package edu.mines.csci448.pcm.blockbrawl.data.database

import androidx.room.TypeConverter
import java.util.*

class BlockBrawlTypeConverters {
    @TypeConverter
    fun fromUUID(uuid: UUID?) = uuid?.toString()

    @TypeConverter
    fun toUUID(uuid: String?) = UUID.fromString(uuid)
}
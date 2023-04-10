package edu.mines.csci448.pcm.blockbrawl.data.database

import android.content.Context
import androidx.room.*
import edu.mines.csci448.pcm.blockbrawl.data.BlockBrawlLevel

@Database(entities=[BlockBrawlLevel::class], version = 1)
@TypeConverters(BlockBrawlTypeConverters::class)
abstract class BlockBrawlDatabase : RoomDatabase (){

    companion object {
        @Volatile private  var INSTANCE: BlockBrawlDatabase? = null
        fun getInstance(context: Context): BlockBrawlDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context = context,
                        klass = BlockBrawlDatabase::class.java,
                        name = "blockbrawl-database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    abstract val blockBrawlDao: BlockBrawlDao
}
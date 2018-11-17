package com.ehret.devoxxmorocco.model

import android.content.Context
import androidx.room.*
import java.util.*

@Entity(tableName = "speaker")
data class Speaker(
    @ColumnInfo val firstName: String,
    @ColumnInfo val lastName: String,
    @PrimaryKey val uuid: String = UUID.randomUUID().toString(),
    @ColumnInfo val country: String = "MOROCCO"
)


@Dao
interface SpeakerDao {

    @Insert
    fun create(speaker: Speaker)

    @Update
    fun update(speaker: Speaker)

    @Query("select * from speaker")
    fun readAll(): List<Speaker>

    @Query("select * from speaker where uuid = :id")
    fun readById(id: String): Speaker

    @Delete
    fun delete(speaker: Speaker)
}

@Database(entities = arrayOf(Speaker::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun speakerDao(): SpeakerDao

    companion object {
        var INSTANCE: AppDatabase? = null

        fun instance(context: Context, inMemory: Boolean = false): AppDatabase {
            if (INSTANCE == null) {
                if (inMemory) {
                    INSTANCE = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
                } else {
                    INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, "DevoxxMa3").build()
                }
            } else if (!INSTANCE!!.isOpen && inMemory) {
                INSTANCE = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
            }

            return INSTANCE!!
        }
    }
}


package com.wahidabd.mangain.data.networks.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wahidabd.mangain.data.models.room.BookmarkData
import com.wahidabd.mangain.data.models.room.HistoryData
import com.wahidabd.mangain.data.models.room.ReadData

@Database(entities = [BookmarkData::class, HistoryData::class, ReadData::class], version = 2, exportSchema = false)
abstract class MyDatabase: RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
    abstract fun historyDao(): HistoryDao
    abstract fun readDao(): ReadDao

    companion object{
        @Volatile
        private var instance: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase =
            instance ?: synchronized(this){
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, MyDatabase::class.java, "mangain.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
    }

}
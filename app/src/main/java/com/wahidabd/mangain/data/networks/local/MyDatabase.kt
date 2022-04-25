package com.wahidabd.mangain.data.networks.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wahidabd.mangain.data.models.room.BookmarkData
import com.wahidabd.mangain.data.models.room.HistoryData

@Database(entities = [BookmarkData::class, HistoryData::class], version = 1, exportSchema = false)
abstract class MyDatabase: RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
    abstract fun historyDao(): HistoryDao

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
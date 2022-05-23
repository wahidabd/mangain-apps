package com.wahidabd.mangain.data.networks.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wahidabd.mangain.data.models.room.HistoryData
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history_entity ORDER BY updated_at DESC")
    fun getAllHistory(): Flow<List<HistoryData>>

    @Query("SELECT * FROM history_entity WHERE id = :id")
    fun getHistoryById(id: String?): Flow<HistoryData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHistory(data: HistoryData)

    @Query("UPDATE history_entity SET id_chapter = :id_chapter AND chapter = :chapter WHERE id = :id")
    fun updateChapter(id: String?, id_chapter: String?, chapter: String?)

    @Query("DELETE FROM history_entity WHERE id = :id")
    fun deleteHistoryById(id: String?)

    @Query("DELETE FROM history_entity")
    fun deleteHistory()

    @Query("DELETE FROM history_entity WHERE id NOT IN (SELECT id FROM history_entity ORDER BY updated_at LIMIT 50)")
    fun autoDelete()
}
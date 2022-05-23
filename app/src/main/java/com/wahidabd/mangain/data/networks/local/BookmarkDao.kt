package com.wahidabd.mangain.data.networks.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wahidabd.mangain.data.models.room.BookmarkData
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao{
    @Query("SELECT * FROM bookmark_entity ORDER BY updated_at DESC")
    fun getAllBookmark(): Flow<List<BookmarkData>>

    @Query("SELECT * FROM bookmark_entity WHERE id = :id")
    fun getBookmarkById(id: String?): Flow<BookmarkData>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBookmark(data: BookmarkData)

    @Query("DELETE FROM bookmark_entity")
    fun deleteBookmark()

    @Query("DELETE FROM bookmark_entity WHERE id = :id")
    fun deleteBookmarkById(id: String?)
}
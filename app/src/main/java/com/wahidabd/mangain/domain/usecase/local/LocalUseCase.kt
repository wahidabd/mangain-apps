package com.wahidabd.mangain.domain.usecase.local

import com.wahidabd.mangain.data.models.room.BookmarkData
import com.wahidabd.mangain.data.models.room.HistoryData
import kotlinx.coroutines.flow.Flow

interface LocalUseCase {
    fun getAllBookmark(): Flow<List<BookmarkData>>
    fun getBookmarkById(id: String?): Flow<BookmarkData>
    fun insertBookmark(data: BookmarkData)
    fun deleteBookmark(id: String?)

    fun getAllHistory(): Flow<List<HistoryData>>
    fun getHistoryById(id: String?): Flow<HistoryData>
    fun insertHistory(data: HistoryData)
    fun deleteHistory(id: String?)
    fun updateHistory(id: String?, id_chapter: String?, chapter: String?)
    fun autoDelete()
}
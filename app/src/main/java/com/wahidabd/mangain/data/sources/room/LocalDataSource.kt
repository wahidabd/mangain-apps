package com.wahidabd.mangain.data.sources.room

import com.wahidabd.mangain.data.models.room.BookmarkData
import com.wahidabd.mangain.data.models.room.HistoryData
import com.wahidabd.mangain.data.models.room.ReadData
import com.wahidabd.mangain.data.networks.local.MyDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val database: MyDatabase) {

    fun getBookmark(): Flow<List<BookmarkData>> = database.bookmarkDao().getAllBookmark()
    fun getBookmarkById(id: String?) = database.bookmarkDao().getBookmarkById(id)
    fun insertBookmark(data: BookmarkData) = database.bookmarkDao().insertBookmark(data)
    fun deleteBookmark(id: String?) = database.bookmarkDao().deleteBookmarkById(id)
    fun deleteAllBookmark() = database.bookmarkDao().deleteBookmark()

    fun getHistory(): Flow<List<HistoryData>> = database.historyDao().getAllHistory()
    fun getHistoryById(id: String?) = database.historyDao().getHistoryById(id)
    fun insertHistory(data: HistoryData) = database.historyDao().insertHistory(data)
    fun deleteHistory(id: String?) = database.historyDao().deleteHistoryById(id)
    fun updateHistory(id: String?, id_chapter: String?, chapter: String?) = database.historyDao().updateChapter(id, id_chapter, chapter)
    fun deleteAllHistory() = database.historyDao().deleteHistory()
    fun autoDelete() = database.historyDao().autoDelete()

    fun insertRead(data: ReadData) = database.readDao().insertRead(data)
    fun readById(id: String?) = database.readDao().readById(id)
}
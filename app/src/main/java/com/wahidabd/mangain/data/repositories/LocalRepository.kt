package com.wahidabd.mangain.data.repositories

import com.wahidabd.mangain.data.models.room.BookmarkData
import com.wahidabd.mangain.data.models.room.HistoryData
import com.wahidabd.mangain.data.models.room.ReadData
import com.wahidabd.mangain.data.sources.room.LocalDataSource
import com.wahidabd.mangain.domain.repository.LocalRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val database: LocalDataSource
) : LocalRepositoryImpl {

    override fun getAllBookmark(): Flow<List<BookmarkData>> = database.getBookmark()
    override fun getBookmarkById(id: String?): Flow<BookmarkData> = database.getBookmarkById(id)
    override fun insertBookmark(data: BookmarkData) = database.insertBookmark(data)
    override fun deleteBookmark(id: String?) = database.deleteBookmark(id)
    override fun deleteAllBookmark() = database.deleteAllBookmark()

    override fun getAllHistory(): Flow<List<HistoryData>> = database.getHistory()
    override fun getHistoryById(id: String?): Flow<HistoryData> = database.getHistoryById(id)
    override fun insertHistory(data: HistoryData) = database.insertHistory(data)
    override fun deleteHistory(id: String?) = database.deleteHistory(id)
    override fun updateHistory(id: String?, id_chapter: String?, chapter: String?) = database.updateHistory(id, id_chapter, chapter)
    override fun deleteAllHistory() = database.deleteAllHistory()
    override fun autoDelete() = database.autoDelete()

    override fun insertRead(data: ReadData) = database.insertRead(data)
    override fun readById(id: String?): Flow<ReadData> = database.readById(id)
}
package com.wahidabd.mangain.domain.usecase.local

import com.wahidabd.mangain.data.models.room.BookmarkData
import com.wahidabd.mangain.data.models.room.HistoryData
import com.wahidabd.mangain.data.models.room.ReadData
import com.wahidabd.mangain.domain.repository.LocalRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalInteractor @Inject constructor(
    private val repo: LocalRepositoryImpl
): LocalUseCase {
    override fun getAllBookmark(): Flow<List<BookmarkData>> = repo.getAllBookmark()
    override fun getBookmarkById(id: String?): Flow<BookmarkData> = repo.getBookmarkById(id)
    override fun insertBookmark(data: BookmarkData) = repo.insertBookmark(data)
    override fun deleteBookmark(id: String?) = repo.deleteBookmark(id)
    override fun deleteAllBookmark() = repo.deleteAllBookmark()

    override fun getAllHistory(): Flow<List<HistoryData>> = repo.getAllHistory()
    override fun getHistoryById(id: String?): Flow<HistoryData> = repo.getHistoryById(id)
    override fun insertHistory(data: HistoryData) = repo.insertHistory(data)
    override fun deleteHistory(id: String?) = repo.deleteHistory(id)
    override fun updateHistory(id: String?, id_chapter: String?, chapter: String?) = repo.updateHistory(id, id_chapter, chapter)
    override fun deleteAllHistory() = repo.deleteAllHistory()
    override fun autoDelete() = repo.autoDelete()

    override fun insertRead(data: ReadData) = repo.insertRead(data)
    override fun readById(id: String?): Flow<ReadData> = repo.readById(id)
}
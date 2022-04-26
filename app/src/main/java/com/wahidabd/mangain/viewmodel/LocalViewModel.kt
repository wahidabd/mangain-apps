package com.wahidabd.mangain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.wahidabd.mangain.data.models.room.BookmarkData
import com.wahidabd.mangain.data.models.room.HistoryData
import com.wahidabd.mangain.domain.usecase.local.LocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocalViewModel @Inject constructor(
    private val useCase: LocalUseCase
) : ViewModel() {

    val allBookmark = useCase.getAllBookmark().asLiveData()
    fun bookmarkById(id: String?): LiveData<BookmarkData> = useCase.getBookmarkById(id).asLiveData()
    fun insertBookmark(data: BookmarkData) = useCase.insertBookmark(data)
    fun deleteBookmark(id: String?)= useCase.deleteBookmark(id)

    val allHistory = useCase.getAllHistory().asLiveData()
    fun historyById(id: String?): LiveData<HistoryData> = useCase.getHistoryById(id).asLiveData()
    fun insertHistory(data: HistoryData) = useCase.insertHistory(data)
    fun deleteHistory(id: String?)= useCase.deleteHistory(id)
    fun updateHistory(id: String?, id_chapter: String?, chapter: String?) = useCase.updateHistory(id, id_chapter, chapter)
    val autoDelete = useCase.autoDelete()
}
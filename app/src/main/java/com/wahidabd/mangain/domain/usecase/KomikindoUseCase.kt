package com.wahidabd.mangain.domain.usecase

import androidx.paging.PagingData
import com.wahidabd.mangain.core.Resource
import com.wahidabd.mangain.data.models.Komik
import com.wahidabd.mangain.data.responses.ChapterResponse
import com.wahidabd.mangain.data.responses.KomikindoHomeResponse
import com.wahidabd.mangain.data.responses.MangaDetailResponse
import kotlinx.coroutines.flow.Flow

interface KomikindoUseCase {

    fun komik(): Flow<PagingData<Komik>>
    fun manhwa(): Flow<PagingData<Komik>>
    fun manhua(): Flow<PagingData<Komik>>
    fun daftar(): Flow<PagingData<Komik>>

    fun home(): Flow<Resource<KomikindoHomeResponse>>
    fun detail(id: String): Flow<Resource<MangaDetailResponse>>
    fun chapter(id: String): Flow<Resource<ChapterResponse>>
}
package com.wahidabd.mangain.domain.repository

import com.wahidabd.mangain.core.Resource
import com.wahidabd.mangain.data.responses.ChapterResponse
import com.wahidabd.mangain.data.responses.KomikindoHomeResponse
import com.wahidabd.mangain.data.responses.MangaDetailResponse
import kotlinx.coroutines.flow.Flow

interface KomikindoRepositoryImpl {

    fun home(): Flow<Resource<KomikindoHomeResponse>>
    fun detail(id: String): Flow<Resource<MangaDetailResponse>>
    fun chapter(id: String): Flow<Resource<ChapterResponse>>

}
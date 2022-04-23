package com.wahidabd.mangain.domain.usecase

import com.wahidabd.mangain.core.Resource
import com.wahidabd.mangain.data.responses.ChapterResponse
import com.wahidabd.mangain.data.responses.KomikindoHomeResponse
import com.wahidabd.mangain.data.responses.MangaDetailResponse
import kotlinx.coroutines.flow.Flow

interface KomikindoUseCase {

    fun home(): Flow<Resource<KomikindoHomeResponse>>
    fun detail(id: String): Flow<Resource<MangaDetailResponse>>
    fun chapter(id: String): Flow<Resource<ChapterResponse>>


}
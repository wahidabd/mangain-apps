package com.wahidabd.mangain.data.repositories

import com.wahidabd.mangain.core.Resource
import com.wahidabd.mangain.data.responses.ChapterResponse
import com.wahidabd.mangain.data.responses.KomikindoHomeResponse
import com.wahidabd.mangain.data.responses.MangaDetailResponse
import com.wahidabd.mangain.data.sources.remote.KomikindoDataSource
import com.wahidabd.mangain.domain.repository.KomikindoRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class KomikindoRepository @Inject constructor(private val data: KomikindoDataSource): KomikindoRepositoryImpl {

    override fun home(): Flow<Resource<KomikindoHomeResponse>> =
        data.home()

    override fun detail(id: String): Flow<Resource<MangaDetailResponse>> =
        data.detail(id)

    override fun chapter(id: String): Flow<Resource<ChapterResponse>> =
        data.chapter(id)
}
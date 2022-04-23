package com.wahidabd.mangain.domain.usecase

import com.wahidabd.mangain.core.Resource
import com.wahidabd.mangain.data.responses.ChapterResponse
import com.wahidabd.mangain.data.responses.KomikindoHomeResponse
import com.wahidabd.mangain.data.responses.MangaDetailResponse
import com.wahidabd.mangain.domain.repository.KomikindoRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class KomikindoInteractor @Inject constructor(private val repo: KomikindoRepositoryImpl): KomikindoUseCase {

    override fun home(): Flow<Resource<KomikindoHomeResponse>> =
        repo.home()

    override fun detail(id: String): Flow<Resource<MangaDetailResponse>> =
        repo.detail(id)

    override fun chapter(id: String): Flow<Resource<ChapterResponse>> =
        repo.chapter(id)
}
package com.wahidabd.mangain.domain.usecase.komik

import androidx.paging.PagingData
import com.wahidabd.mangain.core.Resource
import com.wahidabd.mangain.data.models.Komik
import com.wahidabd.mangain.data.responses.ChapterResponse
import com.wahidabd.mangain.data.responses.KomikindoHomeResponse
import com.wahidabd.mangain.data.responses.MangaDetailResponse
import com.wahidabd.mangain.domain.repository.KomikindoRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class KomikindoInteractor @Inject constructor(private val repo: KomikindoRepositoryImpl):
    KomikindoUseCase {
    override fun komik(): Flow<PagingData<Komik>> =
        repo.komik()

    override fun manhwa(): Flow<PagingData<Komik>> =
        repo.manhwa()

    override fun manhua(): Flow<PagingData<Komik>> =
        repo.manhua()

    override fun daftar(): Flow<PagingData<Komik>> =
        repo.daftar()

    override fun search(s: String): Flow<PagingData<Komik>> =
        repo.search(s)

    override fun home(): Flow<Resource<KomikindoHomeResponse>> =
        repo.home()

    override fun detail(id: String): Flow<Resource<MangaDetailResponse>> =
        repo.detail(id)

    override fun chapter(id: String): Flow<Resource<ChapterResponse>> =
        repo.chapter(id)
}
package com.wahidabd.mangain.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.wahidabd.mangain.core.Resource
import com.wahidabd.mangain.data.models.Komik
import com.wahidabd.mangain.data.networks.service.KomikindoService
import com.wahidabd.mangain.data.responses.ChapterResponse
import com.wahidabd.mangain.data.responses.KomikindoHomeResponse
import com.wahidabd.mangain.data.responses.MangaDetailResponse
import com.wahidabd.mangain.data.sources.remote.KomikindoDataSource
import com.wahidabd.mangain.data.sources.remote.KomikindoPagingSource
import com.wahidabd.mangain.domain.repository.KomikindoRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class KomikindoRepository @Inject constructor(
    private val data: KomikindoDataSource,
    private val service: KomikindoService
): KomikindoRepositoryImpl {

    override fun komik(): Flow<PagingData<Komik>> =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ), pagingSourceFactory = {KomikindoPagingSource(service)}
        ).flow

    override fun manhwa(): Flow<PagingData<Komik>> =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ), pagingSourceFactory = {KomikindoPagingSource(service, manhwa = true)}
        ).flow


    override fun manhua(): Flow<PagingData<Komik>> =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ), pagingSourceFactory = {KomikindoPagingSource(service, manhua = true)}
        ).flow


    override fun daftar(): Flow<PagingData<Komik>> =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ), pagingSourceFactory = {KomikindoPagingSource(service, daftar = true)}
        ).flow

    override fun search(s: String): Flow<PagingData<Komik>> =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ), pagingSourceFactory = {KomikindoPagingSource(service, query = s)}
        ).flow

    override fun home(): Flow<Resource<KomikindoHomeResponse>> =
        data.home()

    override fun detail(id: String): Flow<Resource<MangaDetailResponse>> =
        data.detail(id)

    override fun chapter(id: String): Flow<Resource<ChapterResponse>> =
        data.chapter(id)
}
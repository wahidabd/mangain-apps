package com.wahidabd.mangain.data.sources.remote

import com.wahidabd.mangain.core.ErrorParser
import com.wahidabd.mangain.core.Resource
import com.wahidabd.mangain.core.SafeCall
import com.wahidabd.mangain.data.networks.service.KomikindoService
import com.wahidabd.mangain.data.responses.ChapterResponse
import com.wahidabd.mangain.data.responses.KomikindoHomeResponse
import com.wahidabd.mangain.data.responses.MangaDetailResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class KomikindoDataSource @Inject constructor(
    private val service: KomikindoService,
    private val errorParser: ErrorParser,
    private val safeCall: SafeCall
) {

    fun home(): Flow<Resource<KomikindoHomeResponse>> = flow {
        emit(Resource.loading(null))

        val res = safeCall.enqueue(errorParser::converterErrorGeneric, service::home)
        emit(res)
    }.flowOn(Dispatchers.IO)

    fun detail(id: String): Flow<Resource<MangaDetailResponse>> = flow {
        emit(Resource.loading(null))

        val res = safeCall.enqueue(id, errorParser::converterErrorGeneric, service::detail)
        emit(res)
    }.flowOn(Dispatchers.IO)

    fun chapter(id: String): Flow<Resource<ChapterResponse>> = flow {
        emit(Resource.loading(null))

        val res = safeCall.enqueue(id, errorParser::converterErrorGeneric, service::chapter)
        emit(res)
    }.flowOn(Dispatchers.IO)

}
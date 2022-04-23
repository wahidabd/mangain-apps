package com.wahidabd.mangain.data.networks.service

import com.wahidabd.mangain.data.responses.ChapterResponse
import com.wahidabd.mangain.data.responses.KomikindoHomeResponse
import com.wahidabd.mangain.data.responses.MangaDetailResponse
import com.wahidabd.mangain.utils.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface KomikindoService {

    @GET(Constant.KOMIKINDO_HOME)
    suspend fun home(): Response<KomikindoHomeResponse>

    @GET(Constant.KOMIKINDO_DETAIL)
    suspend fun detail(
        @Path("id") id: String
    ): Response<MangaDetailResponse>

    @GET(Constant.KOMIKINDO_CH)
    suspend fun chapter(
        @Path("id") id: String
    ): Response<ChapterResponse>
}
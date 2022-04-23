package com.wahidabd.mangain.data.responses

import com.wahidabd.mangain.data.models.MangaDetail

data class MangaDetailResponse(
    val status: String,
    val manga: MangaDetail
)
package com.wahidabd.mangain.data.responses

import com.wahidabd.mangain.data.models.ChapterDetail

data class ChapterResponse(
    val status: String,
    val chapter: ChapterDetail
)
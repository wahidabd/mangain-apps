package com.wahidabd.mangain.data.models

data class ChapterDetail(
    val title: String? = null,
    val prev: String? = null,
    val next: String? = null,
    val data: List<DataChapterDetail>
)

data class DataChapterDetail(
    val num: Int,
    val img: String
)

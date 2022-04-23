package com.wahidabd.mangain.data.models

data class ChapterDetail(
    val title: String,
    val prev: String,
    val next: String? = null,
    val data: List<DataChapterDetail>
)

data class DataChapterDetail(
    val num: Int,
    val img: String
)

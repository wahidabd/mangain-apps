package com.wahidabd.mangain.data.models

data class MangaDetail(
    val title: String,
    val banner: String? = null,
    val cover: String? = null,
    val status: String,
    val author: String,
    val ilustrator: String,
    val grafis: String,
    val type: String,
    val rating: String,
    val vote: String,
    val synopsis: String,
    val genres: List<Genre>,
    val eps: List<Chapter>
)
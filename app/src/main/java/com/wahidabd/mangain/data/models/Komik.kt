package com.wahidabd.mangain.data.models

data class Komik(
    val id: String,
    val title: String,
    val cover: String,
    val type: String,
    val update_on: String? = null,
    val rating: String? = null
)
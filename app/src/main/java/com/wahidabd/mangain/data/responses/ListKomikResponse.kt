package com.wahidabd.mangain.data.responses

import com.wahidabd.mangain.data.models.Komik

data class ListKomikResponse(
    val status: String,
    val data: List<Komik>
)

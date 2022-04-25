package com.wahidabd.mangain.data.responses

import com.wahidabd.mangain.data.models.NewManga
import com.wahidabd.mangain.data.models.Popular
import com.wahidabd.mangain.data.models.PopularDay

data class KomikindoHomeResponse(
    val status: String,
    val home: KomikindoDataHomeResponse
)

data class KomikindoDataHomeResponse(
    val new_manga: List<NewManga>,
    val popular_day: List<PopularDay>,
    val popular: List<Popular>
)

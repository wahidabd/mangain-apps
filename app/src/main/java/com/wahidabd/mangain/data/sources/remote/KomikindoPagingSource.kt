package com.wahidabd.mangain.data.sources.remote

import com.wahidabd.mangain.data.networks.service.KomikindoService
import javax.inject.Inject

class KomikindoPagingSource @Inject constructor(
    private val service: KomikindoService,
    private val komik: Boolean? = null,
    private val manhwa: Boolean? = null,
    private val manhua: Boolean? = null,
) {
}
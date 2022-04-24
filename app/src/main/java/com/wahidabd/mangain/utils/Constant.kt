package com.wahidabd.mangain.utils

import com.wahidabd.mangain.data.models.Chapter

object Constant {

    // for api
    const val KIRYUU_HOME = "home"
    const val KIRYUU_NEW_UPDATE = "kiryuu-new-update/{page}"
    const val KIRYUU_MANGA_LIST = "kiryuu-manga-list/{page}"
    const val KIRYUU_MANHWA_LIST = "kiryuu-manhwa-list/{page}"
    const val KIRYUU_MANHUA_LIST = "kiryuu-manhua-list/{page}"
    const val KIRYUU_DETAIL = "kiryuu-detail/{id}"
    const val KIRYUU_CHAPTER = "kiryuu-chapter/{id}"
    const val KIRYUU_SEARCH = "kiryuu-search/{q}/page/{page}"
    const val KIRYUU_GENRES = "kiryuu-genres"
    const val KIRYUU_GENRE = "kiryuu-genre/{id}/page/{page}"

    const val KOMIKINDO_HOME = "home"
    const val KOMIKINDO_DETAIL = "komikindo-detail/{id}"
    const val KOMIKINDO_CH = "komikindo-ch/{id}"
    const val KOMIKINDO_GENRES = "komikindo-genres/{id}"
    const val KOMIKINDO_GENRE_LIST = "komikindo-genre/{id}/page/{page}"
    const val KOMIKINDO_DAFTAR = "komikindo-daftar/{page}"
    const val KOMIKINDO_MANHWA = "komikindo-manhwa/{page}"
    const val KOMIKINDO_MANHUA = "komikindo-manhua/{page}"
    const val KOMIKINDO_KOMIK = "komikindo-komik/{page}"
    const val KOMIKINDO_SEARCH = "komikindo-komik/{s}/page/{page}"


    // depend manga manhua manhwa
    const val MANGA = "Manga"
    const val MANHWA = "Manhwa"
    const val MANHUA = "Manhua"

    const val STARTING_PAGE_INDEX = 1
}
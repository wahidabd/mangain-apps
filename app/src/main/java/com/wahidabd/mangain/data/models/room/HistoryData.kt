package com.wahidabd.mangain.data.models.room

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_entity")
data class HistoryData(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "id_chapter")
    val id_chapter: String? = null,

    @ColumnInfo(name = "title")
    val title: String? = null,

    @ColumnInfo(name = "cover")
    val cover: String? = null,

    @ColumnInfo(name = "type")
    val type: String? = null,

    @ColumnInfo(name = "chapter")
    val chapter: String? = null,

    @ColumnInfo(name = "updated_at")
    val updated_at: String? = null
)
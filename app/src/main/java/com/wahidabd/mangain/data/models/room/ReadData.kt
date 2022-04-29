package com.wahidabd.mangain.data.models.room

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "read_entity")
data class ReadData(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: String,
)
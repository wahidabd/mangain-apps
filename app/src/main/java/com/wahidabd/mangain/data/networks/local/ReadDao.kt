package com.wahidabd.mangain.data.networks.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wahidabd.mangain.data.models.room.ReadData
import kotlinx.coroutines.flow.Flow

@Dao
interface ReadDao {
    @Query("SELECT * FROM read_entity")
    fun getAllRead(): Flow<List<ReadData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertRead(data: ReadData)
}
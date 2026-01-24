package com.manahu.manager.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchDataDao {
    @Query("SELECT * FROM MatchData")
    fun getAll(): Flow<List<MatchData>>

    @Insert
    suspend fun insert(matchdata: MatchData)

    @Delete
    suspend fun delete(matchdata: MatchData)

    @Query("DELETE FROM MatchData")
    suspend fun deleteAll()
     @Upsert
    suspend fun upsert(matchdata: MatchData)
}
package com.manahu.manager.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MatchData(
    val homename : String,
    val awayname: String,
    val tournament : String,
    val venue : String
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
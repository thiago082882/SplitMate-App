package br.thiago.splitmateapp.data.local


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "splits")
data class SplitEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val total: Double,
    val people: Int
)

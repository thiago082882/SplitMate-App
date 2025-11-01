package br.thiago.splitmateapp.data.local


import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SplitEntity::class], version = 1)
abstract class SplitDatabase : RoomDatabase() {
    abstract fun splitDao(): SplitDao
}

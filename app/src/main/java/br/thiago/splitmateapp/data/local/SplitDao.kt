package br.thiago.splitmateapp.data.local


import androidx.room.*

@Dao
interface SplitDao {
    @Query("SELECT * FROM splits ORDER BY id DESC")
    suspend fun getAllSplits(): List<SplitEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSplit(split: SplitEntity)

    @Delete
    suspend fun deleteSplit(split: SplitEntity)
}

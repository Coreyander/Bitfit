package com.example.codepath_project_5_bitfit.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FitnessDAO {
    @Query("SELECT id FROM fitness_table")
    fun getUserID(): Flow<List<Long>>

    @Query("SELECT * FROM fitness_table")
    fun getAll(): LiveData<List<FitnessEntity>>

    @Query("SELECT mood FROM fitness_table")
    fun getMood(): MutableList<String?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry : FitnessEntity)

    @Delete
    suspend fun deleteThis(entry : FitnessEntity?)
}
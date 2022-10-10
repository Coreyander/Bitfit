package com.example.codepath_project_5_bitfit.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date


@Entity(tableName = "fitness_table")
data class FitnessEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "mood") val mood: String?,
    @ColumnInfo(name = "sleep") val sleep: String?,
    @ColumnInfo(name = "sleep_goal") val sleep_goal: String?,
    @ColumnInfo(name = "water") val water: String?,
    @ColumnInfo(name = "water_goal") val waterGoal: String?,
    @ColumnInfo(name = "food_name") val food: String?,
    @ColumnInfo(name = "food_calories") val calories: String?,
    @ColumnInfo(name = "food_goal") val foodGoal: String?
)


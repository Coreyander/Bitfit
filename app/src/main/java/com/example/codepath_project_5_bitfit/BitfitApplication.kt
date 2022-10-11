package com.example.codepath_project_5_bitfit

import android.app.Application
import com.example.codepath_project_5_bitfit.database.AppDatabase
import com.example.codepath_project_5_bitfit.database.FitnessRepository

class BitfitApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this) }
    val repo by lazy { FitnessRepository(db.fitnessDao())}
}
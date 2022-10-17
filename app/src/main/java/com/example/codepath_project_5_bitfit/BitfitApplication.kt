package com.example.codepath_project_5_bitfit

import android.app.Application
import com.example.codepath_project_5_bitfit.database.AppDatabase

class BitfitApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}
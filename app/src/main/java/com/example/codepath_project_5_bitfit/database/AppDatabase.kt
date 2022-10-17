package com.example.codepath_project_5_bitfit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * @Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
abstract fun userDao(): UserDao
}
 */

@Database(entities = [FitnessEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun fitnessDao() : FitnessDAO

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "Fitness-db"
            ).fallbackToDestructiveMigration()
                .build()
    }
}
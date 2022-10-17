package com.example.codepath_project_5_bitfit

import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import com.example.codepath_project_5_bitfit.database.FitnessEntity
import kotlinx.coroutines.launch

class BitFitViewModel(): ViewModel() {

    private var _mood = MutableLiveData<String>()
    val mood: LiveData<String> = _mood
    fun setMoodData(newMood: String) {
        _mood.value = newMood
    }
    private var _sleep = MutableLiveData<Float>()
    val sleep: LiveData<Float> = _sleep
    fun setSleepData(newSleep: Float) {
        _sleep.value = newSleep
    }
    private var _water = MutableLiveData<Float>()
    val water: LiveData<Float> = _water
    fun setWaterData(newWater: Float) {
        _water.value = newWater
    }
    private var _calories = MutableLiveData<Int>()
    val calories: LiveData<Int> = _calories
    fun setCaloriesData(newCalories: Int) {
        _calories.value = newCalories
    }

}
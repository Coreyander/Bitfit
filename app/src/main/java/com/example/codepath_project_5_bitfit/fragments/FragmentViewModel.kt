package com.example.codepath_project_5_bitfit.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.codepath_project_5_bitfit.database.FitnessEntity

class FragmentViewModel: ViewModel() {
    private var _entries = listOf<FitnessEntity>()
    var entries: List<FitnessEntity> = _entries
    fun setData(databaseData : List<FitnessEntity>) {
        entries = databaseData
    }
    fun getData(): List<FitnessEntity> {
        return entries
    }


    private var _mood = MutableLiveData<String>()
    val mood: LiveData<String> = _mood
    fun setMood(newMood : String) { _mood.value = newMood
        _mood
    }


    private var sleep : String? = null
    fun setSleep(sleep : String) { this.sleep = sleep }
    fun getSleep(): String? { return sleep }

}
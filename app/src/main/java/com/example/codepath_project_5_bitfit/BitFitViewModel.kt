package com.example.codepath_project_5_bitfit

import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import com.example.codepath_project_5_bitfit.database.FitnessEntity
import com.example.codepath_project_5_bitfit.database.FitnessRepository
import kotlinx.coroutines.launch

class BitFitViewModel(private val repository: FitnessRepository): ViewModel() {

    //Repository is making the DAO call to the database
    val entries: LiveData<List<FitnessEntity>> = repository.entries

    fun insert(entry: FitnessEntity) = viewModelScope.launch {
        repository.insert(entry)
    }

    private var _mood = MutableLiveData<String>()
    val mood: LiveData<String> = _mood
    fun setMoodData(newMood: String) {
        _mood.value = newMood
    }

}


class ViewModelFactory(private val repository: FitnessRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BitFitViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BitFitViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
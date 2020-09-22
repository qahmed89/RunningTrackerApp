package com.example.runningtrackerapp.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runningtrackerapp.db.Run
import com.example.runningtrackerapp.other.SortBy
import com.example.runningtrackerapp.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(val mainRepository: MainRepository) : ViewModel() {

    private val runSortByDate = mainRepository.getAllRunsSortedByDate()
    private val runSortByRunningTime = mainRepository.getAllRunsSortedByTimeInMillis()
    private val runSortByDistance = mainRepository.getAllRunsSortedByDistance()
    private val runSortByAverageSpeed = mainRepository.getAllRunsSortedByAvgSpeed()
    private val runSortByCalories = mainRepository.getAllRunsSortedByCaloriesBurned()
    val runs = MediatorLiveData<List<Run>>()
    var sortType = SortBy.DATE

    init {
        runs.addSource(runSortByDate) { result ->
            if (sortType == SortBy.DATE) {
                result?.let {
                    runs.value = it
                }
            }
        }
            runs.addSource(runSortByRunningTime) { result ->

                if (sortType == SortBy.RUNNING_TIME) {
                    result?.let {
                        runs.value = it
                    }
                }
            }
            runs.addSource(runSortByDistance) { result ->

                if (sortType == SortBy.DISTANCE) {
                    result?.let {
                        runs.value = it
                    }
                }
            }
            runs.addSource(runSortByAverageSpeed) { result ->

                if (sortType == SortBy.AVG_SPEED) {
                    result?.let {
                        runs.value = it
                    }
                }
            }
            runs.addSource(runSortByCalories) { result ->

                if (sortType == SortBy.CALORIES_BURN) {
                    result?.let {
                        runs.value = it
                    }
                }
            }


    }

    fun sortRuns(sortBy: SortBy) = when (sortBy) {
        SortBy.DATE -> runSortByDate.value?.let { runs.value = it }
        SortBy.RUNNING_TIME -> runSortByRunningTime.value?.let { runs.value = it }
        SortBy.AVG_SPEED -> runSortByAverageSpeed.value?.let { runs.value = it }
        SortBy.DISTANCE -> runSortByDistance.value?.let { runs.value = it }
        SortBy.CALORIES_BURN -> runSortByCalories.value?.let { runs.value = it }

    }.also {
        this.sortType = sortBy
    }

    fun insertRun(run: Run) = viewModelScope.launch {
        mainRepository.runDao.insertRun(run)
    }

}
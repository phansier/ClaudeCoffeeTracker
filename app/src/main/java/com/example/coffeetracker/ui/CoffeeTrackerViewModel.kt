package com.example.coffeetracker.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeetracker.domain.Coffee
import com.example.coffeetracker.domain.CoffeeRepository
import com.example.coffeetracker.domain.CoffeeSize
import com.example.coffeetracker.domain.CoffeeTrackerUiState
import com.example.coffeetracker.domain.CoffeeType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoffeeTrackerViewModel @Inject constructor(
    private val repository: CoffeeRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(CoffeeTrackerUiState())
    val uiState: StateFlow<CoffeeTrackerUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                repository.getTodayCoffees(),
                repository.getWeekCoffees()
            ) { todayCoffees, weekCoffees ->
                CoffeeTrackerUiState(
                    todayCoffees = todayCoffees,
                    weekCoffees = weekCoffees,
                    dailyStats = calculateDailyStats(todayCoffees),
                    weeklyStats = calculateWeeklyStats(weekCoffees)
                )
            }.collect { state ->
                _uiState.value = state
            }
        }
    }

    fun addCoffee(type: CoffeeType, size: CoffeeSize) {
        viewModelScope.launch {
            repository.addCoffee(type, size)
        }
    }

    fun deleteCoffee(coffee: Coffee) {
        viewModelScope.launch {
            repository.deleteCoffee(coffee)
        }
    }
}
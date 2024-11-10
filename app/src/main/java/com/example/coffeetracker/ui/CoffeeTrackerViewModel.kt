package com.example.coffeetracker.ui

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
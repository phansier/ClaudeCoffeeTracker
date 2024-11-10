package com.example.coffeetracker.domain

data class DailyStats(
    val totalCaffeine: Int,
    val totalVolume: Int,
    val totalCups: Int,
)

data class WeeklyStats(
    val averageCups: Int,
    val totalVolume: Int,
    val totalCups: Int,
)

enum class TrackerTab(val title: String) {
    DAILY("Daily"),
    WEEKLY("Weekly")
}

data class CoffeeTrackerUiState(
    val dailyStats: DailyStats = DailyStats(0, 0, 0),
    val weeklyStats: WeeklyStats = WeeklyStats(0, 0, 0),
    val todayCoffees: List<Coffee> = emptyList(),
    val weekCoffees: List<Coffee> = emptyList(),
)
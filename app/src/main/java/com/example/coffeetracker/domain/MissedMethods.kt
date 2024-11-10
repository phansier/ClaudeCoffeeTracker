package com.example.coffeetracker.domain

import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

fun updateChartData(chart: BarChart, coffees: List<Coffee>) {
    val data = coffees.groupBy { it.type }
    val entries = data.map { (type, coffees) ->
        val volume = coffees.sumOf { it.size.multiplier * type.volume }
        val caffeine = coffees.sumOf { it.size.multiplier * type.caffeine }
        BarEntry(volume.toFloat(), caffeine.toFloat(), type.displayName)
    }
    chart.data = BarData(BarDataSet(entries, "Caffeine"))
}

fun calculateWeeklyStats(weekCoffees: List<Coffee>): WeeklyStats {
    val totalCups = weekCoffees.size
    val totalVolume = weekCoffees.sumOf { it.size.multiplier * it.type.volume }
    val averageCups = totalCups / 7
    return WeeklyStats(averageCups, totalVolume, totalCups)
}

fun calculateDailyStats(todayCoffees: List<Coffee>): DailyStats {
    val totalCups = todayCoffees.size
    val totalVolume = todayCoffees.sumOf { it.size.multiplier * it.type.volume }
    val totalCaffeine = todayCoffees.sumOf { it.size.multiplier * it.type.caffeine }
    return DailyStats(totalCaffeine, totalVolume, totalCups)
}
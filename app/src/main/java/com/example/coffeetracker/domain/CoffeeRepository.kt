package com.example.coffeetracker.domain

import kotlinx.coroutines.flow.Flow

interface CoffeeRepository {
    fun getTodayCoffees(): Flow<List<Coffee>>
    fun getWeekCoffees(): Flow<List<Coffee>>
    suspend fun addCoffee(type: CoffeeType, size: CoffeeSize)
    suspend fun deleteCoffee(coffee: Coffee)
}
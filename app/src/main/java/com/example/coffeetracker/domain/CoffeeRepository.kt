package com.example.coffeetracker.domain

import com.example.coffeetracker.data.CoffeeDao
import com.example.coffeetracker.data.CoffeeEntity
import com.example.coffeetracker.data.toDomain
import com.example.coffeetracker.data.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface CoffeeRepository {
    fun getTodayCoffees(): Flow<List<Coffee>>
    fun getWeekCoffees(): Flow<List<Coffee>>
    suspend fun addCoffee(type: CoffeeType, size: CoffeeSize)
    suspend fun deleteCoffee(coffee: Coffee)
}

class CoffeeRepositoryImpl constructor(
    private val dao: CoffeeDao
) : CoffeeRepository {
    override fun getTodayCoffees(): Flow<List<Coffee>> {
        return dao.getTodayCoffees().map { it.map { it.toDomain() } }
    }


    override fun getWeekCoffees(): Flow<List<Coffee>> {
        return dao.getCoffeesForDateRange(
            startTime = System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000,
            endTime = System.currentTimeMillis()
        ).map { it.map { it.toDomain() } }
    }

    override suspend fun addCoffee(type: CoffeeType, size: CoffeeSize) {
        dao.insertCoffee(CoffeeEntity(type = type.name, size = size.multiplier))
    }

    override suspend fun deleteCoffee(coffee: Coffee) {
        dao.deleteCoffee(coffee.toEntity())
    }
}
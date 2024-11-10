package com.example.coffeetracker.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CoffeeDao {
    @Query("SELECT * FROM coffees WHERE date(timestamp/1000, 'unixepoch') = date('now')")
    fun getTodayCoffees(): Flow<List<CoffeeEntity>>

    @Query("SELECT * FROM coffees WHERE timestamp >= :startTime AND timestamp <= :endTime")
    fun getCoffeesForDateRange(startTime: Long, endTime: Long): Flow<List<CoffeeEntity>>

    @Insert
    suspend fun insertCoffee(coffee: CoffeeEntity)

    @Delete
    suspend fun deleteCoffee(coffee: CoffeeEntity)
}
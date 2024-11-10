package com.example.coffeetracker.data

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
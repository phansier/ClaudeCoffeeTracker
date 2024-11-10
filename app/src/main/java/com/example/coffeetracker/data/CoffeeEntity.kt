package com.example.coffeetracker.data

data class CoffeeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val type: String,
    val size: Int,
    val volume: Int,
    val caffeine: Int,
    val timestamp: Long = System.currentTimeMillis()
)
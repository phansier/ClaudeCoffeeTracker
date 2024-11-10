package com.example.coffeetracker.domain

data class Coffee(
    val id: Long,
    val type: CoffeeType,
    val size: CoffeeSize,
    val timestamp: Long
)

enum class CoffeeType(val volume: Int, val caffeine: Int, val displayName: String) {
    ESPRESSO(30, 63, "Espresso"),
    AMERICANO(200, 63, "Americano"),
    LATTE(250, 63, "Latte"),
    CAPPUCCINO(180, 63, "Cappuccino"),
    MOCHA(250, 63, "Mocha"),
    FILTER(240, 95, "Filter Coffee")
}

enum class CoffeeSize(val multiplier: Int, val displayName: String) {
    SINGLE(1, "Single"),
    DOUBLE(2, "Double"),
    TRIPLE(3, "Triple")
}
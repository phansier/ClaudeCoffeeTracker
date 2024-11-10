package com.example.coffeetracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.coffeetracker.domain.Coffee
import com.example.coffeetracker.domain.CoffeeSize
import com.example.coffeetracker.domain.CoffeeType

@Entity(tableName = "coffees")
data class CoffeeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val type: String,
    val size: Int,
    val timestamp: Long = System.currentTimeMillis()
)


fun Coffee.toEntity() = CoffeeEntity(
    id = id,
    type = type.name,
    size = size.multiplier,
    timestamp = timestamp
)

fun CoffeeEntity.toDomain() = Coffee(
    id = id,
    type = CoffeeType.valueOf(type),
    size = CoffeeSize.values().first { it.multiplier == size },
    timestamp = timestamp
)
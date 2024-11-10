package com.example.coffeetracker.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.coffeetracker.data.CoffeeDao
import com.example.coffeetracker.data.CoffeeEntity
import com.example.coffeetracker.domain.CoffeeRepository
import com.example.coffeetracker.domain.CoffeeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideCoffeeDatabase(@ApplicationContext context: Context): CoffeeDatabase {
        return Room.databaseBuilder(
            context,
            CoffeeDatabase::class.java,
            "coffee_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCoffeeRepository(db: CoffeeDatabase): CoffeeRepository {
        return CoffeeRepositoryImpl(db.coffeeDao())
    }
}

@Database(
    entities = [CoffeeEntity::class],
    version = 1
)
abstract class CoffeeDatabase : RoomDatabase() {
    abstract fun coffeeDao(): CoffeeDao
}
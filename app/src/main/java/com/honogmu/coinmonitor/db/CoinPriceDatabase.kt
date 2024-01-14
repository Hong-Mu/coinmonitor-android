package com.honogmu.coinmonitor.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.honogmu.coinmonitor.db.dao.InterestCoinDao
import com.honogmu.coinmonitor.db.entity.InterestCoinEntity

@Database(entities = [InterestCoinEntity::class], version = 1)
abstract class CoinPriceDatabase : RoomDatabase() {
    abstract fun interestCoinDao(): InterestCoinDao

    companion object {
        @Volatile
        private var INSTANCE: CoinPriceDatabase? = null

        fun getInstance(context: Context): CoinPriceDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CoinPriceDatabase::class.java,
                    "coin_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
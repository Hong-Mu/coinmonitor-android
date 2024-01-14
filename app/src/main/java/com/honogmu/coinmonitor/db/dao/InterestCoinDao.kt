package com.honogmu.coinmonitor.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.honogmu.coinmonitor.db.entity.InterestCoinEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InterestCoinDao {
    @Query("select * from interest_coin")
    fun getAll(): Flow<List<InterestCoinEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(interestCoinEntity: InterestCoinEntity)

    @Update
    fun update(interestCoinEntity: InterestCoinEntity)

    @Query("select * from interest_coin where selected = :selected")
    fun getSelected(selected: Boolean = true): Flow<List<InterestCoinEntity>>
}
package com.honogmu.coinmonitor.repository

import com.honogmu.coinmonitor.MyApplication
import com.honogmu.coinmonitor.db.CoinPriceDatabase
import com.honogmu.coinmonitor.db.entity.InterestCoinEntity

class DBRepository {
    val context = MyApplication.getContext()
    val db = CoinPriceDatabase.getInstance(context)

    fun getAllInterestCoin() = db.interestCoinDao().getAll()

    fun insertInterestCoin(interestCoinEntity: InterestCoinEntity) =
        db.interestCoinDao().insert(interestCoinEntity)

    fun updateInterestCoin(interestCoinEntity: InterestCoinEntity) =
        db.interestCoinDao().update(interestCoinEntity)

    // TODO: Selected -> Later
    fun getAllInterestSelectedCoin(selected: Boolean = true) = db.interestCoinDao().getSelected(selected)
}
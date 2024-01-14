package com.honogmu.coinmonitor.repository

import com.honogmu.coinmonitor.network.Api
import com.honogmu.coinmonitor.network.RetrofitInstance

class NetworkRepository {
    private val client = RetrofitInstance.getInstance().create(Api::class.java)

    suspend fun getCurrentCoinList() = client.getCurrentCoinList()
}
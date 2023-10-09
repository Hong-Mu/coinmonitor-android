package com.hongmu.coinmonitor.network

class NetworkRepository {
    private val coinApi = RetrofitHelper.getInstance().create(CoinAPI::class.java)

    suspend fun getAll() = coinApi.getAll()
}
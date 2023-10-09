package com.hongmu.coinmonitor.network

import com.hongmu.coinmonitor.network.model.CoinListResponse
import retrofit2.http.GET

interface CoinAPI {
    @GET("public/ticker/ALL_KRW")
    suspend fun getAll(): CoinListResponse
}
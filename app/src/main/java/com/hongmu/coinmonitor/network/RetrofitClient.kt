package com.hongmu.coinmonitor.network

import com.google.gson.GsonBuilder
import com.hongmu.coinmonitor.network.model.CoinListResponse
import com.hongmu.coinmonitor.network.util.CoinListTypeAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private const val BASE_URL = "https://api.bithumb.com/"

    private val gson = GsonBuilder()
        .registerTypeAdapter(CoinListResponse::class.java, CoinListTypeAdapter())
        .create()

    private val client = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun getInstance(): Retrofit = client
}
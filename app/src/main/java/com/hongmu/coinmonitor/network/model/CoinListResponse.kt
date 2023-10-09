package com.hongmu.coinmonitor.network.model

//@JsonAdapter(CoinListAdapter::class)
data class CoinListResponse(
    val status: String,
    val data: List<CoinInfo>,
)

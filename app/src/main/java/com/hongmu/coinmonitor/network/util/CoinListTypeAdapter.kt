package com.hongmu.coinmonitor.network.util

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import com.hongmu.coinmonitor.network.model.CoinInfo
import com.hongmu.coinmonitor.network.model.CoinListResponse
import java.lang.reflect.Type

class CoinListTypeAdapter : JsonDeserializer<CoinListResponse> {

    private val gson = Gson()

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CoinListResponse {

        val jsonObject = json?.asJsonObject!!
        jsonObject.get("data")?.asJsonObject?.remove("date")

        val status = jsonObject.get("status")?.asString!!

        val type = object: TypeToken<Map<String, CoinInfo>>(){}.type
        val coinDataMap = gson.fromJson<Map<String, CoinInfo>>(jsonObject.get("data"), type).map {
            it.value.name = it.key
            it.value
        }


        return CoinListResponse(status, coinDataMap)
    }
}
package com.honogmu.coinmonitor.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.honogmu.coinmonitor.dataModel.CurrentPrice
import com.honogmu.coinmonitor.dataModel.CurrentPriceResult
import com.honogmu.coinmonitor.dataStore.MyDataStore
import com.honogmu.coinmonitor.db.entity.InterestCoinEntity
import com.honogmu.coinmonitor.repository.DBRepository
import com.honogmu.coinmonitor.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class SelectViewModel : ViewModel() {
    private val networkRepository = NetworkRepository()
    private val dbRepository = DBRepository()
    private lateinit var currentPriceResultList: ArrayList<CurrentPriceResult>

    private val _currentPriceResult = MutableLiveData<List<CurrentPriceResult>>()
    val currentPriceResult: LiveData<List<CurrentPriceResult>>
        get() = _currentPriceResult

    private val _saved = MutableLiveData<Boolean>()
    val saved: LiveData<Boolean>
        get() = _saved

    fun getCurrentCoinList() = viewModelScope.launch {
        val result = networkRepository.getCurrentCoinList()

        currentPriceResultList = ArrayList()
        for (coin in result.data) {
            try {
                val gson = Gson()
                val gsonToJson = gson.toJson(result.data.get(coin.key))
                val gsonFromJson = gson.fromJson(gsonToJson, CurrentPrice::class.java)

                val currentPriceResult = CurrentPriceResult(coin.key, gsonFromJson)
                currentPriceResultList.add(currentPriceResult)
            } catch (e: Exception) {
                Timber.d(e)
            }
        }
        _currentPriceResult.value = currentPriceResultList
    }

    fun updateFlag() = viewModelScope.launch {
        MyDataStore().updateIntroFlagToTrue()
    }

    fun saveSelectedCoinList(selectedCoinList: List<String>) =
        viewModelScope.launch(Dispatchers.IO) {
            for (coin in currentPriceResultList) {
                val selected = selectedCoinList.contains(coin.coinName)

                val interestCoinEntity = InterestCoinEntity(
                    0,
                    coin.coinName,
                    coin.coinInfo.acc_trade_value,
                    coin.coinInfo.acc_trade_value_24H,
                    coin.coinInfo.closing_price,
                    coin.coinInfo.fluctate_24H,
                    coin.coinInfo.fluctate_rate_24H,
                    coin.coinInfo.max_price,
                    coin.coinInfo.min_price,
                    coin.coinInfo.opening_price,
                    coin.coinInfo.prev_closing_price,
                    coin.coinInfo.units_traded,
                    coin.coinInfo.units_traded_24H,
                    selected,
                )

                dbRepository.insertInterestCoin(interestCoinEntity)
            }

            _saved.postValue(true)
        }
}
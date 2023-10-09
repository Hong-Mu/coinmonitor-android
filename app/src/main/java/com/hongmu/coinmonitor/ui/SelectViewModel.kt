package com.hongmu.coinmonitor.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hongmu.coinmonitor.network.NetworkRepository
import com.hongmu.coinmonitor.network.model.CoinInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SelectViewModel: ViewModel() {

    private val networkRepository = NetworkRepository()

    private lateinit var _coinList: List<CoinInfo>
    private val _coinListLiveData = MutableLiveData<List<CoinInfo>>()
    val coinListLiveData: LiveData<List<CoinInfo>>
        get() = _coinListLiveData

    fun getAll() = viewModelScope.launch(Dispatchers.IO) {

        _coinList = networkRepository.getAll().data
        _coinListLiveData.postValue(_coinList)
    }
}
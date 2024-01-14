package com.honogmu.coinmonitor.view.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.honogmu.coinmonitor.db.entity.InterestCoinEntity
import com.honogmu.coinmonitor.repository.DBRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val dBRepository = DBRepository()

    // TODO: Flow to LiveData (lateinit removed) -> prevent uninitialized exception
    val interestCoin = dBRepository.getAllInterestCoin().asLiveData()
    val selectedInterestCoin = dBRepository.getAllInterestSelectedCoin(true).asLiveData()
    val unSelectedInterestCoin = dBRepository.getAllInterestSelectedCoin(false).asLiveData()

    fun updateInterestCoin(interestCoinEntity: InterestCoinEntity) = viewModelScope.launch(Dispatchers.IO) {
        // TODO: Separate Logic
        dBRepository.updateInterestCoin(interestCoinEntity)
    }

}
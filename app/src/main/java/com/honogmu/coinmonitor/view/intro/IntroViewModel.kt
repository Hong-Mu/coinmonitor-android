package com.honogmu.coinmonitor.view.intro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.honogmu.coinmonitor.dataStore.MyDataStore
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class IntroViewModel: ViewModel() {
    private val _isNotFirst = MutableLiveData<Boolean>()
    val isNotFirst: LiveData<Boolean>
        get() = _isNotFirst

    fun checkFlag() = viewModelScope.launch {
        delay(2000)
        val flag = MyDataStore().getIntroFlag()
        _isNotFirst.value = flag
    }
}
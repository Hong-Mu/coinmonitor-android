package com.honogmu.coinmonitor

import android.app.Application
import timber.log.Timber

class MyApplication: Application() {
    companion object {
        private var instance: MyApplication? = null

        fun getContext() = instance!!.applicationContext
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
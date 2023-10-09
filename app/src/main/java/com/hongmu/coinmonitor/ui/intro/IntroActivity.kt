package com.hongmu.coinmonitor.ui.intro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.gson.Gson
import com.hongmu.coinmonitor.R
import com.hongmu.coinmonitor.network.model.CoinListResponse
import timber.log.Timber


class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen() // 반드시 super() 보다 먼저 실행해야 함
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

    }
}
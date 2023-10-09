package com.hongmu.coinmonitor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.hongmu.coinmonitor.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Timber.d("onCreate")
    }
}
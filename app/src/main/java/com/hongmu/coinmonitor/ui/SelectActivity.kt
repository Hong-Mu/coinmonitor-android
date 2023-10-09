package com.hongmu.coinmonitor.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.hongmu.coinmonitor.databinding.ActivitySelectBinding
import com.hongmu.coinmonitor.network.util.CoinListTypeAdapter
import com.hongmu.coinmonitor.ui.adapter.SelectRVAdapter
import timber.log.Timber

class SelectActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySelectBinding.inflate(layoutInflater) }
    private val viewModel: SelectViewModel by viewModels()

    private lateinit var adapter: SelectRVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        adapter = SelectRVAdapter(this)
        binding.recyclerView.adapter = adapter

        viewModel.coinListLiveData.observe(this) {
            adapter.list = it
        }

        viewModel.getAll()
    }
}
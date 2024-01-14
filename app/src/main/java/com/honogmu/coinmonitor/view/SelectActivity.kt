package com.honogmu.coinmonitor.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.honogmu.coinmonitor.databinding.ActivitySelectBinding
import com.honogmu.coinmonitor.view.adapter.SelectRVAdapter
import com.honogmu.coinmonitor.view.main.MainActivity

class SelectActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySelectBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<SelectViewModel>()
    private lateinit var selectRVAdapter: SelectRVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        selectRVAdapter = SelectRVAdapter(this)
        binding.coinListRV.adapter = selectRVAdapter
        binding.coinListRV.layoutManager = LinearLayoutManager(this)

        viewModel.currentPriceResult.observe(this) {
            selectRVAdapter.list = it
        }
        viewModel.getCurrentCoinList()

        binding.laterTextArea.setOnClickListener {
            viewModel.updateFlag()
            viewModel.saveSelectedCoinList(selectRVAdapter.selectedCoinList)
        }

        viewModel.saved.observe(this) {
            if(it) {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }
}
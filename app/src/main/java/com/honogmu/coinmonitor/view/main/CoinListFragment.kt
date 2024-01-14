package com.honogmu.coinmonitor.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.honogmu.coinmonitor.databinding.FragmentCoinListBinding
import com.honogmu.coinmonitor.view.adapter.CoinListRVAdapter

class CoinListFragment : Fragment() {

    private var _binding: FragmentCoinListBinding? = null
    private val binding get() = _binding!!

    // TODO: Sharing ViewModel
    private val viewModel: MainViewModel by activityViewModels()

    private val selectedRVAdapter by lazy { CoinListRVAdapter() }
    private val unSelectedRVAdapter by lazy { CoinListRVAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinListBinding.inflate(inflater, container, false)
        return binding.root
    }

    // TODO: Write Logic After View Created
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRV()

        viewModel.interestCoin.observe(viewLifecycleOwner) {
            // TODO: Logic
        }

        viewModel.selectedInterestCoin.observe(viewLifecycleOwner) {
            selectedRVAdapter.list = it
        }

        viewModel.unSelectedInterestCoin.observe(viewLifecycleOwner) {
            unSelectedRVAdapter.list = it
        }
    }

    private fun initRV() {
        binding.selectedCoinRV.adapter = selectedRVAdapter
        binding.selectedCoinRV.layoutManager = LinearLayoutManager(context)
        selectedRVAdapter.setOnLikeItemClickListener { position ->
            viewModel.updateInterestCoin(selectedRVAdapter.list[position].apply {
                selected = false
            })
        }

        binding.unSelectedCoinRV.adapter = unSelectedRVAdapter
        binding.unSelectedCoinRV.layoutManager = LinearLayoutManager(context)
        unSelectedRVAdapter.setOnLikeItemClickListener { position ->
            viewModel.updateInterestCoin(unSelectedRVAdapter.list[position].apply {
                selected = true
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
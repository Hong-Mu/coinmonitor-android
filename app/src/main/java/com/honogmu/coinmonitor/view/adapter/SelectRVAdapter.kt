package com.honogmu.coinmonitor.view.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.honogmu.coinmonitor.R
import com.honogmu.coinmonitor.dataModel.CurrentPriceResult
import com.honogmu.coinmonitor.databinding.IntroCoinItemBinding

class SelectRVAdapter(val context: Context) :
    RecyclerView.Adapter<SelectRVAdapter.ViewHolder>() {

    var list: List<CurrentPriceResult> = listOf()
        set(value) {
            field = value // this.list = list
            notifyDataSetChanged()
        }

    var selectedCoinList = mutableListOf<String>()

    inner class ViewHolder(private val binding: IntroCoinItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val item = list[position]

            binding.coinName.text = item.coinName

            val fluctate_24H = item.coinInfo.fluctate_24H
            binding.coinPriceUpDown.text = fluctate_24H
            if(fluctate_24H.contains("-")) {
                binding.coinPriceUpDown.setTextColor(Color.parseColor("#114fed"))
            } else {
                binding.coinPriceUpDown.setTextColor(Color.parseColor("#ed2e11"))
            }

            if(selectedCoinList.contains(item.coinName)) {
                binding.likeBtn.setImageResource(R.drawable.like_red)
            } else {
                binding.likeBtn.setImageResource(R.drawable.like_grey)
            }

            binding.likeBtn.setOnClickListener {
                if(selectedCoinList.contains(item.coinName)) {
                    binding.likeBtn.setImageResource(R.drawable.like_grey)
                    selectedCoinList.remove(item.coinName)
                } else {
                    binding.likeBtn.setImageResource(R.drawable.like_red)
                    selectedCoinList.add(item.coinName)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            IntroCoinItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }
}
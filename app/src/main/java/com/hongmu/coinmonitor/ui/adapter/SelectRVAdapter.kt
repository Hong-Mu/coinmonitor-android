package com.hongmu.coinmonitor.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hongmu.coinmonitor.R
import com.hongmu.coinmonitor.databinding.ItemSelectCoinBinding
import com.hongmu.coinmonitor.network.model.CoinInfo

class SelectRVAdapter(val context: Context): RecyclerView.Adapter<SelectRVAdapter.ViewHolder>() {

    private val likedCoin: MutableMap<String, Boolean> = mutableMapOf()

    var list: List<CoinInfo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSelectCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ViewHolder(private val binding: ItemSelectCoinBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CoinInfo) {
            binding.textName.text = item.name
            binding.textPrice.also {
                it.text = item.fluctate_24H
                val colorResId = if(item.fluctate_24H.contains("-")) R.color.blue else R.color.red
                it.setTextColor(ContextCompat.getColor(context, colorResId))
            }

            updateLike(likedCoin[item.name]?:false)

            binding.imageLike.setOnClickListener {
                likedCoin[item.name] = !(likedCoin[item.name]?:false)
                updateLike(likedCoin[item.name]!!)
            }
        }

        private fun updateLike(liked: Boolean) {
            val resId = if(liked) R.drawable.like_red else R.drawable.like_grey
            binding.imageLike.setImageResource(resId)
        }
    }


}
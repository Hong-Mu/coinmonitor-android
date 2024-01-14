package com.honogmu.coinmonitor.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.honogmu.coinmonitor.R
import com.honogmu.coinmonitor.databinding.ItemMainCoinBinding
import com.honogmu.coinmonitor.db.entity.InterestCoinEntity

class CoinListRVAdapter: RecyclerView.Adapter<CoinListRVAdapter.ViewHolder>() {

    var list: List<InterestCoinEntity> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: ItemMainCoinBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            list[position].let {
                binding.coinName.text = it.coin_name

                if(it.selected) {
                    binding.likeBtn.setImageResource(R.drawable.like_red)
                } else {
                    binding.likeBtn.setImageResource(R.drawable.like_grey)
                }
            }

            binding.likeBtn.setOnClickListener {
                // TODO: Call
                onLikeItemClickListener?.onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMainCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    // TODO: Functional Interface
    fun interface OnLikeItemClickListener {
        fun onItemClick(position: Int)
    }

    private var onLikeItemClickListener: OnLikeItemClickListener? = null

    fun setOnLikeItemClickListener(onLikeItemClickListener: OnLikeItemClickListener) {
        this.onLikeItemClickListener = onLikeItemClickListener
    }
}
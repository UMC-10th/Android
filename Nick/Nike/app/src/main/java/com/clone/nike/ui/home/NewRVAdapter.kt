package com.clone.nike.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clone.nike.databinding.ItemNewBinding

class NewRVAdapter(private var newGoodsList: MutableList<NewGoodsData>)
    : RecyclerView.Adapter<NewRVAdapter.NewViewHolder>()
{
    override fun onCreateViewHolder(
        parent: ViewGroup,
        p1: Int
    ): NewViewHolder {
        val binding = ItemNewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return NewViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: NewRVAdapter.NewViewHolder,
        position: Int
    ) {
        val newGoods = newGoodsList[position]
        holder.bind(newGoods)
    }

    override fun getItemCount(): Int {
        return newGoodsList.size
    }

    inner class NewViewHolder(val binding: ItemNewBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(new: NewGoodsData) {
            binding.apply {
                itemNewIV.setImageResource(new.goodsImgResId)
                itemNewNameTV.text = new.goodsName
                itemNewPriceTV.text = new.goodsPrice
            }
        }
    }

}




package com.clone.nike.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clone.nike.databinding.ItemNewBinding
import com.clone.nike.ui.purchase.GoodsData

class NewRVAdapter(
    private var newGoodsList: MutableList<GoodsData>,
    private var newOnclickListener: NewOnclickListener
    ): RecyclerView.Adapter<NewRVAdapter.NewViewHolder>()
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
        holder.binding.itemNewLayout.setOnClickListener {
            newOnclickListener.newGoodsOnClickListener(newGoods)
        }
    }

    override fun getItemCount(): Int {
        return newGoodsList.size
    }

    inner class NewViewHolder(val binding: ItemNewBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(new: GoodsData) {
            binding.apply {
                itemNewIV.setImageResource(new.goodsImgResId)
                itemNewNameTV.text = new.goodsName
                itemNewPriceTV.text = new.goodsPrice
            }
        }
    }
}

interface NewOnclickListener {
    fun newGoodsOnClickListener(newGoods: GoodsData)
}



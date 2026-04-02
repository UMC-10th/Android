package com.clone.nike.ui.purchase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clone.nike.databinding.ItemGoodsListBinding

class GoodsRVAdapter(private var goodsList: MutableList<GoodsData>)
    : RecyclerView.Adapter<GoodsRVAdapter.GoodsViewHolder>() {

        override fun onCreateViewHolder(
        parent: ViewGroup,
        p1: Int
    ): GoodsViewHolder {
        val binding = ItemGoodsListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return GoodsViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: GoodsViewHolder,
        position: Int
    ) {
        val goodsList = goodsList[position]
        holder.bind(goodsList)
    }

    override fun getItemCount(): Int {
        return goodsList.size
    }

    inner class GoodsViewHolder(val binding: ItemGoodsListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(goods: GoodsData) {
            binding.apply {
                itemPurchaseIV.setImageResource(goods.goodsImgResId)
                itemPurchaseNameTV.text = goods.goodsName
                itemPurchaseDetailTV.text = goods.category
                itemPurchaseColoursTV.text = goods.numberOfColour
                itemPurchasePriceTV.text = goods.goodsPrice
            }
        }
    }
}
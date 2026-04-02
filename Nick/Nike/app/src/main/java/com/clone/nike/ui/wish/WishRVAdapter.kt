package com.clone.nike.ui.wish

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clone.nike.databinding.ItemGoodsListBinding

class WishRVAdapter(private val wishList: MutableList<WishListData>): RecyclerView.Adapter<WishRVAdapter.WishViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        p1: Int
    ): WishViewHolder {
        val binding = ItemGoodsListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return WishViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: WishViewHolder,
        position: Int
    ) {
        holder.bind(wishList[position])
    }

    override fun getItemCount(): Int {
        return wishList.size
    }

    inner class WishViewHolder(val binding: ItemGoodsListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(wish: WishListData) {
            binding.apply {
                itemPurchaseIV.setImageResource(wish.goodsImgResId)
                itemPurchaseNameTV.text = wish.goodsName
                itemPurchaseDetailTV.text = wish.category
                itemPurchaseColoursTV.text = wish.numberOfColour
                itemPurchasePriceTV.text = wish.goodsPrice
            }
        }
    }
}
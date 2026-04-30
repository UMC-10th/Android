package com.clone.nike.ui.wish

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clone.nike.R
import com.clone.nike.databinding.ItemGoodsListBinding
import com.clone.nike.ui.purchase.GoodsData

class WishRVAdapter(
    private val wishList: MutableList<GoodsData>,
    private val wishRVOnclickListener: (GoodsData) -> Unit): RecyclerView.Adapter<WishRVAdapter.WishViewHolder>() {
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
        holder.binding.itemPurchaseLL.setOnClickListener {
            wishRVOnclickListener(wishList[position])
        }
    }

    override fun getItemCount(): Int {
        return wishList.size
    }

    inner class WishViewHolder(val binding: ItemGoodsListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(wish: GoodsData) {
            binding.apply {
                itemPurchaseIV.setImageResource(wish.goodsImgResId)
                itemPurchaseNameTV.text = wish.goodsName
                itemPurchaseDetailTV.text = wish.category
                itemPurchaseColoursTV.text = wish.numberOfColour
                itemPurchasePriceTV.text = wish.goodsPrice

                if (wish.isWished) {
                    itemPurchaseAddWishIV.setImageResource(R.drawable.icon_wish_on)
                }
                else {
                    itemPurchaseAddWishIV.setImageResource(R.drawable.icon_wish_off)
                }
            }
        }
    }
}
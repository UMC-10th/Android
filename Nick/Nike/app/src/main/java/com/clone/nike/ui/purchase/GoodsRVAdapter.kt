package com.clone.nike.ui.purchase

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.clone.nike.R
import com.clone.nike.databinding.ItemGoodsListBinding

class GoodsRVAdapter(
    private var goodsList: MutableList<GoodsData>,
    private val goodsRVOnclickListener: GoodsRVOnclickListener)
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

        holder.apply {
            bind(goodsList)
            binding.itemPurchaseAddWishIV.setOnClickListener {
                goodsRVOnclickListener.wishOnclickListener(goodsList)
                checkWished(goodsList, binding.itemPurchaseAddWishIV)
            }
            binding.itemPurchaseLL.setOnClickListener {
                goodsRVOnclickListener.goodsOnclickListener(goodsList)
                checkWished(goodsList, binding.itemPurchaseAddWishIV)
            }
        }
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

                checkWished(goods, itemPurchaseAddWishIV)
            }
        }
    }

    fun checkWished(goodsData: GoodsData, imageView: ImageView) {
        if (goodsData.isWished) {
            imageView.setImageResource(R.drawable.icon_wish_on)
        }
        else {
            imageView.setImageResource(R.drawable.icon_wish_off)
        }
    }
}

interface GoodsRVOnclickListener {
    fun wishOnclickListener(goods: GoodsData)
    fun goodsOnclickListener(goods: GoodsData)
}
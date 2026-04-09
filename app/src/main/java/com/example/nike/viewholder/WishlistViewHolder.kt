package com.example.nike.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.ProductData
import com.example.nike.databinding.ItemWishlistBinding

class WishlistViewHolder(
    val binding: ItemWishlistBinding,
    private val onItemClicked: (ProductData) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ProductData) {
        // 기존 ProductData 변수명(coverImg, name, price) 절대 사수
        binding.itemWishImgIv.setImageResource(product.coverImg)
        binding.itemWishNameTv.text = product.name
        binding.itemWishPriceTv.text = product.price

        // 설명(desc)이 비어있으면 숨기기, 아니면 보여주기
        if (product.desc.isNullOrEmpty()) {
            binding.itemWishDescTv.visibility = View.GONE
        } else {
            binding.itemWishDescTv.visibility = View.VISIBLE
            binding.itemWishDescTv.text = product.desc
        }

        // 색상(colors)이 비어있으면 숨기기, 아니면 보여주기
        if (product.colors.isNullOrEmpty()) {
            binding.itemWishColorsTv.visibility = View.GONE
        } else {
            binding.itemWishColorsTv.visibility = View.VISIBLE
            binding.itemWishColorsTv.text = product.colors
        }

        binding.root.setOnClickListener {
            onItemClicked(product)
        }
    }
}
package com.example.nike.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.R
import com.example.nike.data.ProductData
import com.example.nike.databinding.ItemProductBinding

class ProductViewHolder(val binding: ItemProductBinding) :
RecyclerView.ViewHolder(binding.root) {
    fun bind(product: ProductData) {
        binding.productImage.setImageResource(product.image)
        binding.productName.text = product.name
        binding.productDescription.text = product.description
        binding.productColor.text = product.color
        binding.productPrice.text = product.price

        // 베스트셀러 여부
        if (product.isBestSeller) {
            binding.productBest.visibility = View.VISIBLE
        } else {
            binding.productBest.visibility = View.GONE
        }

        // 좋아요
        if (product.isLiked == null) {
            binding.btnLike.visibility = View.GONE
        } else {
            binding.btnLike.visibility = View.VISIBLE

            val heartIcon = if (product.isLiked == true) {
                R.drawable.ic_like_fill
            } else {
                R.drawable.ic_like_empty
            }
            binding.btnLike.setImageResource(heartIcon)
        }

    }
}
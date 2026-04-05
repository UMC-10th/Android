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
        binding.productPrice.text = product.price

        if (product.isBestSeller == true) {
            binding.productBest.visibility = View.VISIBLE
            binding.spacerBestName.visibility = View.VISIBLE
        } else {
            binding.productBest.visibility = View.GONE
            binding.spacerBestName.visibility = View.GONE
        }

        if (product.description.isNullOrEmpty()) {
            binding.productDescription.visibility = View.GONE
            binding.spacerNameDesc.visibility = View.GONE
        } else {
            binding.productDescription.visibility = View.VISIBLE
            binding.productDescription.text = product.description
            binding.spacerNameDesc.visibility = View.VISIBLE
        }

        if (product.color.isNullOrEmpty()) {
            binding.productColor.visibility = View.GONE
            binding.spacerDescColor.visibility = View.GONE
            binding.spacerColorPrice.visibility = View.GONE
        } else {
            binding.productColor.visibility = View.VISIBLE
            binding.productColor.text = product.color

            binding.spacerDescColor.visibility = if (product.description.isNullOrEmpty()) View.GONE else View.VISIBLE
            binding.spacerColorPrice.visibility = View.VISIBLE
        }

        // 5. 좋아요
        if (product.isLiked == null) {
            binding.btnLike.visibility = View.GONE
        } else {
            binding.btnLike.visibility = View.VISIBLE
            val heartIcon = if (product.isLiked == true) R.drawable.ic_like_fill else R.drawable.ic_like_empty
            binding.btnLike.setImageResource(heartIcon)
        }
    }
}
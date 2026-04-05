package com.example.nike.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.R
import com.example.nike.data.CheckoutData
import com.example.nike.databinding.ItemProductBinding

class CheckoutViewHolder(val binding: ItemProductBinding) :
RecyclerView.ViewHolder(binding.root) {
    fun bind(product: CheckoutData) {
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
        val heartIcon = if (product.isLiked) {
            R.drawable.ic_like_fill // 꽉 찬 하트 리소스
        } else {
            R.drawable.ic_like_empty  // 빈 하트 리소스
        }
        binding.btnLike.setImageResource(heartIcon)

    }
}
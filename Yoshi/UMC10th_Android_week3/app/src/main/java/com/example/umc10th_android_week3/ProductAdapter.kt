package com.example.umc10th_android_week3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.umc10th_android_week3.databinding.ItemProductBinding

class ProductAdapter(private val productList: MutableList<ProductData>)
    : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding: ItemProductBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductData) {
            binding.ivProduct.setImageResource(product.productImage)
            binding.tvProductName.text = product.productName
            binding.tvDescription.text = product.description
            binding.tvColorCount.text = product.colorCount
            binding.tvPrice.text = product.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val nowProduct = productList[position]
        holder.bind(nowProduct)
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
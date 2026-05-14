package com.example.NikeApp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.NikeApp.R
import com.example.NikeApp.data.model.ProductData
import com.example.NikeApp.databinding.ItemProductBinding

class ProductAdapter(
    private val onHeartClicked: (Int, ProductData) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var productList: List<ProductData> = emptyList()

    fun submitList(newList: List<ProductData>) {
        productList = newList
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(val binding: ItemProductBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductData, position: Int) {
            binding.ivProduct.setImageResource(product.productImage)
            binding.tvProductName.text = product.productName
            binding.tvDescription.text = product.description
            binding.tvColorCount.text = product.colorCount
            binding.tvPrice.text = product.price

            // 하트 상태에 따라 아이콘 변경
            if (product.isLiked) {
                binding.ivHeart.setImageResource(R.drawable.ic_heart_on)
            } else {
                binding.ivHeart.setImageResource(R.drawable.ic_heart_off)
            }

            // 하트 클릭 시
            binding.ivHeart.setOnClickListener {
                product.isLiked = !product.isLiked
                notifyItemChanged(position)
                onHeartClicked(position, product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position], position)
    }

    override fun getItemCount(): Int = productList.size
}

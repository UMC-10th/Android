package com.example.nike.core.component.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.core.component.product.ProductData
import com.example.nike.databinding.ItemProductBinding
import com.example.nike.core.component.product.ProductViewHolder

class ProductAdapter(
    private val onVisitClicked: (ProductData) -> Unit,
    private val onLikeClicked: (ProductData) -> Unit
) : ListAdapter<ProductData, ProductViewHolder>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<ProductData>() {
        override fun areItemsTheSame(oldItem: ProductData, newItem: ProductData) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: ProductData, newItem: ProductData) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        binding.root.layoutParams.width = parent.measuredWidth / 2
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position)) { clicked ->
            onLikeClicked(clicked)
        }
    }
}
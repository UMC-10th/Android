package com.example.nike.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.data.ProductData
import com.example.nike.databinding.ItemProductBinding
import com.example.nike.viewholder.ProductViewHolder

class ProductAdapter(
    private var productList: List<ProductData>,
    private val onVisitClicked: (ProductData) -> Unit,
    private val onLikeClicked: (ProductData) -> Unit
    ) : RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)

        val parentWidth = parent.measuredWidth

        val layoutParams = binding.root.layoutParams
        layoutParams.width = parentWidth / 2
        binding.root.layoutParams = layoutParams

        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = productList[position]
        holder.bind(item) { clickedProduct ->
            onLikeClicked(clickedProduct)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

}
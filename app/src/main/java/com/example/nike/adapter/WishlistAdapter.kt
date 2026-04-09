package com.example.nike.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.ProductData
import com.example.nike.databinding.ItemWishlistBinding
import com.example.nike.viewholder.WishlistViewHolder

class WishlistAdapter(
    private val itemList: ArrayList<ProductData>,
    private val onItemClick: (ProductData) -> Unit
) : RecyclerView.Adapter<WishlistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistViewHolder {
        val binding = ItemWishlistBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return WishlistViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: WishlistViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size
}
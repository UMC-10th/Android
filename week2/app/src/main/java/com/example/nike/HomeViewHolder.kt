package com.example.nike

import androidx.recyclerview.widget.RecyclerView
import com.example.nike.databinding.ItemNewestItemBinding

class HomeViewHolder(val binding: ItemNewestItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(newestItem: HomeData) {
        binding.newestItemImage.setImageResource(newestItem.image)
        binding.newestItemName.text = newestItem.name
        binding.newestItemPrice.text = newestItem.price
    }
}
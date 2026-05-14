package com.example.nike.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.core.data.model.HomeData
import com.example.nike.databinding.ItemNewestItemBinding

class HomeAdapter(
    private val onVisitClicked: (HomeData) -> Unit
) : ListAdapter<HomeData, HomeViewHolder>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<HomeData>() {

        override fun areItemsTheSame(oldItem: HomeData, newItem: HomeData): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: HomeData, newItem: HomeData): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemNewestItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
package com.example.nike

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.databinding.ItemNewestItemBinding

class HomeAdapter(
    private var newestItemsList: MutableList<HomeData>,
    private val onVisitClicked: (HomeData) -> Unit
) : RecyclerView.Adapter<HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemNewestItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val nowItem = newestItemsList[position]
        holder.bind(nowItem)
    }

    override fun getItemCount(): Int {
        return newestItemsList.size
    }

}
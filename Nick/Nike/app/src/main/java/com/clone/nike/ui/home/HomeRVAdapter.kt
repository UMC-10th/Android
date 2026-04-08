package com.clone.nike.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.clone.nike.databinding.ItemHomeBinding
import com.clone.nike.ui.purchase.GoodsData

class HomeRVAdapter(
    private var newGoodsDataList: MutableList<GoodsData>,
    private val title: String?,
    private var newOnclickListener: NewOnclickListener
): RecyclerView.Adapter<HomeRVAdapter.HomeViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        p1: Int
    ): HomeViewHolder {
        val binding = ItemHomeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: HomeViewHolder,
        p1: Int
    ) {
        holder.bind(title)
    }

    override fun getItemCount(): Int = 1

    inner class HomeViewHolder(val binding: ItemHomeBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(title: String?) {
            val context = itemView.context

            binding.homeTitleTV.text = title

            val adapter = NewRVAdapter(newGoodsDataList, newOnclickListener)
            binding.homeNewRV.adapter = adapter
            binding.homeNewRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }
}
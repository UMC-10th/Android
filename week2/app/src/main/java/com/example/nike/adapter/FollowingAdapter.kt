package com.example.nike.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nike.data.dto.ProfileResponse
import com.example.nike.databinding.ItemFollowingBinding

class FollowingAdapter(private var items: List<ProfileResponse>) :
    RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>() {

    class FollowingViewHolder(val binding: ItemFollowingBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        val binding = ItemFollowingBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FollowingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        val item = items[position]

        Glide.with(holder.itemView.context)
            .load(item.avatar)
            .circleCrop()
            .into(holder.binding.followingThumb)
    }

    override fun getItemCount() = items.size

    fun updateData(newItems: List<ProfileResponse>) {
        items = newItems
        notifyDataSetChanged()
    }
}
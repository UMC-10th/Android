package com.example.nike.presentation.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nike.core.data.dto.ProfileResponse
import com.example.nike.databinding.ItemFollowingBinding

class FollowingAdapter : ListAdapter<ProfileResponse, FollowingAdapter.FollowingViewHolder>(DiffCallback()) {

    class FollowingViewHolder(val binding: ItemFollowingBinding) :
        RecyclerView.ViewHolder(binding.root)

    class DiffCallback : DiffUtil.ItemCallback<ProfileResponse>() {
        override fun areItemsTheSame(oldItem: ProfileResponse, newItem: ProfileResponse) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ProfileResponse, newItem: ProfileResponse) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        val binding = ItemFollowingBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FollowingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(getItem(position).avatar)
            .circleCrop()
            .into(holder.binding.followingThumb)
    }
}
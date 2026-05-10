package com.example.NikeApp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.NikeApp.data.model.UserData
import com.example.NikeApp.databinding.ItemFollowingBinding

class FollowingAdapter
    : RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>() {

    private var userList: List<UserData> = emptyList()

    fun submitList(newList: List<UserData>) {
        userList = newList
        notifyDataSetChanged()
    }

    inner class FollowingViewHolder(val binding: ItemFollowingBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UserData) {
            // Glide를 사용해 URL 이미지 로딩
            Glide.with(binding.root.context)
                .load(user.avatar)
                .into(binding.ivFollowing)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        val binding = ItemFollowingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return FollowingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int = userList.size
}

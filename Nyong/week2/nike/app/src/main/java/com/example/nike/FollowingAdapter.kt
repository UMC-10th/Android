package com.example.nike

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nike.databinding.ItemFollowingBinding

class FollowingAdapter(
    private var users: List<ReqresUser>
) : RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>() {

    inner class FollowingViewHolder(
        private val binding: ItemFollowingBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: ReqresUser) {
            Glide.with(binding.ivFollowingAvatar.context)
                .load(user.avatar)
                .into(binding.ivFollowingAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        val binding = ItemFollowingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FollowingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    fun updateUsers(newUsers: List<ReqresUser>) {
        users = newUsers
        notifyDataSetChanged()
    }
}
package com.clone.nike.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.clone.nike.R
import com.clone.nike.api.data.response.MyPageResponse
import com.clone.nike.databinding.ItemFollowingBinding
import com.clone.nike.databinding.ItemHomeBinding

class FollowingRVAdapter: RecyclerView.Adapter<FollowingRVAdapter.FollowingViewHolder>() {

    private var imgRes: List<String> = emptyList()

    fun setData(newList: List<String>) {
        imgRes = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        p1: Int
    ): FollowingRVAdapter.FollowingViewHolder {
        val binding = ItemFollowingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return FollowingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowingRVAdapter.FollowingViewHolder, p1: Int) {
        Glide.with(holder.binding.followingProfileIV.context)
            .load(imgRes[p1])
            .placeholder(R.drawable.ic_avatar)
            .error(R.drawable.ic_avatar)
            .into(holder.binding.followingProfileIV)
    }

    override fun getItemCount(): Int = imgRes.size

    class FollowingViewHolder(val binding: ItemFollowingBinding): RecyclerView.ViewHolder(binding.root)
}
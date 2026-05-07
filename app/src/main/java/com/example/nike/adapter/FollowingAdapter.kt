package com.example.nike.adapter

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FollowingAdapter(private val avatarList: List<String>) : RecyclerView.Adapter<FollowingAdapter.ViewHolder>() {

    inner class ViewHolder(private val imageView: ImageView) : RecyclerView.ViewHolder(imageView) {
        fun bind(avatarUrl: String) {
            // Glide 라이브러리가 URL을 비동기로 다운받아 imageView에 쏙 넣어줍니다.
            Glide.with(imageView.context)
                .load(avatarUrl)
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val iv = ImageView(parent.context).apply {
            layoutParams = ViewGroup.MarginLayoutParams(250, 250).apply {
                setMargins(0, 0, 30, 0)
            }
            scaleType = ImageView.ScaleType.CENTER_CROP
        }
        return ViewHolder(iv)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(avatarList[position])
    }

    override fun getItemCount(): Int = avatarList.size
}
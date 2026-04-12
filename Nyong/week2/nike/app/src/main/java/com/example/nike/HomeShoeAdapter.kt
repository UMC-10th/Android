package com.example.nike

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.databinding.ItemHomeShoeBinding

class HomeShoeAdapter(private val shoeList: ArrayList<ShoeData>) : RecyclerView.Adapter<HomeShoeAdapter.HomeShoeViewHolder>() {

    // 1. 포장 직원 (ItemHomeShoeBinding 도면 사용)
    inner class HomeShoeViewHolder(private val binding: ItemHomeShoeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(shoe: ShoeData) {
            binding.ivShoeImage.setImageResource(shoe.imageResId)
            binding.tvShoeName.text = shoe.name
            binding.tvShoeSubtitle.text = shoe.subTitle
            binding.tvShoeColors.text = shoe.colors
            binding.tvShoePrice.text = shoe.price
        }
    }

    // 2. 빈 상자 만들기
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeShoeViewHolder {
        val binding = ItemHomeShoeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeShoeViewHolder(binding)
    }

    // 3. 상자에 데이터 넣으라고 지시하기
    override fun onBindViewHolder(holder: HomeShoeViewHolder, position: Int) {
        holder.bind(shoeList[position])
    }

    // 4. 총 물량 보고
    override fun getItemCount(): Int = shoeList.size
}
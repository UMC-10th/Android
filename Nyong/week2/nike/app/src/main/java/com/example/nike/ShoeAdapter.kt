package com.example.nike

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.databinding.ItemShoeBinding

class ShoeAdapter(private val shoeList: ArrayList<ShoeData>) : RecyclerView.Adapter<ShoeAdapter.ShoeViewHolder>() {

    // 1. 포장 직원: 상자에 진짜 데이터 갈아 끼우기
    inner class ShoeViewHolder(private val binding: ItemShoeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(shoe: ShoeData) {
            binding.ivShoeImage.setImageResource(shoe.imageResId) // 이미지 세팅
            binding.tvShoeName.text = shoe.name                   // 이름 세팅
            binding.tvShoeSubtitle.text = shoe.subTitle           // 서브설명 세팅
            binding.tvShoeColors.text = shoe.colors               // 색상 수 세팅
            binding.tvShoePrice.text = shoe.price                 // 가격 세팅
        }
    }

    // 2. 빈 상자 만들기
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeViewHolder {
        val binding = ItemShoeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoeViewHolder(binding)
    }

    // 3. 상자에 데이터 넣으라고 직원에게 지시하기
    override fun onBindViewHolder(holder: ShoeViewHolder, position: Int) {
        holder.bind(shoeList[position])
    }

    // 4. 총 물량 보고
    override fun getItemCount(): Int = shoeList.size
}
package com.example.nike

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.databinding.ItemHomeShoeBinding

class HomeShoeAdapter(
    private var shoeList: ArrayList<ShoeData>
) : RecyclerView.Adapter<HomeShoeAdapter.HomeShoeViewHolder>() {

    // ViewModel에서 새로운 홈 상품 목록이 오면 Adapter 데이터 갱신
    fun updateShoes(newShoes: ArrayList<ShoeData>) {
        shoeList = newShoes
        notifyDataSetChanged()
    }

    // 1. 포장 직원
    inner class HomeShoeViewHolder(
        private val binding: ItemHomeShoeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

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
        val binding = ItemHomeShoeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HomeShoeViewHolder(binding)
    }

    // 3. 상자에 데이터 넣기
    override fun onBindViewHolder(holder: HomeShoeViewHolder, position: Int) {
        holder.bind(shoeList[position])
    }

    // 4. 총 물량
    override fun getItemCount(): Int = shoeList.size
}
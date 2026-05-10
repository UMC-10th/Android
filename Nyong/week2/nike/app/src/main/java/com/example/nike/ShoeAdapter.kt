package com.example.nike

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.databinding.ItemShoeBinding

class ShoeAdapter(
    private var shoeList: ArrayList<ShoeData>,
    private val onHeartClicked: (Int) -> Unit
) : RecyclerView.Adapter<ShoeAdapter.ShoeViewHolder>() {

    fun updateShoes(newShoes: ArrayList<ShoeData>) {
        shoeList = newShoes
        notifyDataSetChanged()
    }

    inner class ShoeViewHolder(
        private val binding: ItemShoeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(shoe: ShoeData) {
            binding.ivShoeImage.setImageResource(shoe.imageResId)
            binding.tvShoeName.text = shoe.name
            binding.tvShoeSubtitle.text = shoe.subTitle
            binding.tvShoeColors.text = shoe.colors
            binding.tvShoePrice.text = shoe.price

            if (shoe.isLiked) {
                binding.btnHeart.setImageResource(R.drawable.ic_heart_on)
            } else {
                binding.btnHeart.setImageResource(R.drawable.ic_heart_off)
            }

            binding.btnHeart.setOnClickListener {
                val position = bindingAdapterPosition

                if (position != RecyclerView.NO_POSITION) {
                    onHeartClicked(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeViewHolder {
        val binding = ItemShoeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ShoeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoeViewHolder, position: Int) {
        holder.bind(shoeList[position])
    }

    override fun getItemCount(): Int = shoeList.size
}
package com.example.nike.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.data.CheckoutData
import com.example.nike.databinding.ItemProductBinding
import com.example.nike.viewholder.CheckoutViewHolder

class CheckoutAdapter(
    private var CheckoutList: MutableList<CheckoutData>,
    private val onVisitClicked: (CheckoutData) -> Unit
    ) : RecyclerView.Adapter<CheckoutViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckoutViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)

        val parentWidth = parent.measuredWidth

        val layoutParams = binding.root.layoutParams
        layoutParams.width = parentWidth / 2
        binding.root.layoutParams = layoutParams

        return CheckoutViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CheckoutViewHolder, position: Int) {
        val nowCheckout = CheckoutList[position]
        holder.bind(nowCheckout)
    }

    override fun getItemCount(): Int {
        return CheckoutList.size
    }

}
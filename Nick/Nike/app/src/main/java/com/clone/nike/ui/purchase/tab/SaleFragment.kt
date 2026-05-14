package com.clone.nike.ui.purchase.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.clone.nike.databinding.FragmentPurchaseAllBinding
import com.clone.nike.databinding.FragmentPurchaseSaleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SaleFragment: Fragment() {
    private lateinit var binding: FragmentPurchaseSaleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPurchaseSaleBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
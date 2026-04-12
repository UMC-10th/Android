package com.clone.nike.ui.purchase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.clone.nike.R
import com.clone.nike.databinding.FragmentPurchaseBinding
import com.clone.nike.ui.repository.DataStoreRepository
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class PurchaseFragment: Fragment() {
    private lateinit var binding: FragmentPurchaseBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPurchaseBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //tabLayout
        val adapter = GoodsTabAdapter(this)
        binding.purchaseTabLayoutVP.adapter = adapter

        TabLayoutMediator(binding.purchaseTabLayoutTL, binding.purchaseTabLayoutVP) { tab, position ->
            tab.text = when (position) {
                0 -> "전체"
                1 -> "Top&T-shirts"
                else -> "sale"
            }
        }.attach()
    }
}
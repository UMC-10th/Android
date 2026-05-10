package com.example.NikeApp.ui.purchase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.NikeApp.R
import com.example.NikeApp.databinding.FragmentPurchaseBinding
import com.example.NikeApp.ui.adapter.ProductAdapter
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PurchaseFragment : Fragment() {

    private var _binding: FragmentPurchaseBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PurchaseViewModel by viewModels()

    private val productAdapter by lazy {
        ProductAdapter { _, product ->
            viewModel.toggleLike(product)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPurchaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 탭 추가 (UI 로직)
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("전체"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Tops & T-Shirts"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Sale"))

        // 탭 선택 리스너 (UI 로직)
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        binding.rvPurchaseProduct.visibility = View.VISIBLE
                        binding.purchaseFragmentContainer.visibility = View.GONE
                    }
                    1 -> {
                        binding.rvPurchaseProduct.visibility = View.GONE
                        binding.purchaseFragmentContainer.visibility = View.VISIBLE
                        childFragmentManager.beginTransaction()
                            .replace(R.id.purchase_fragment_container, TopsFragment())
                            .commit()
                    }
                    2 -> {
                        binding.rvPurchaseProduct.visibility = View.GONE
                        binding.purchaseFragmentContainer.visibility = View.VISIBLE
                        childFragmentManager.beginTransaction()
                            .replace(R.id.purchase_fragment_container, SaleFragment())
                            .commit()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        binding.rvPurchaseProduct.adapter = productAdapter
        binding.rvPurchaseProduct.layoutManager = GridLayoutManager(
            requireContext(), 2
        )

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                productAdapter.submitList(state.products)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

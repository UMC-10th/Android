package com.example.umc10th_android_week4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.umc10th_android_week4.databinding.FragmentPurchaseBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class PurchaseFragment : Fragment() {

    private var _binding: FragmentPurchaseBinding? = null
    private val binding get() = _binding!!
    private lateinit var productStorage: ProductStorage

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPurchaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productStorage = ProductStorage(requireContext())

        // 탭 추가
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("전체"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Tops & T-Shirts"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Sale"))

        // 탭 선택 리스너
        binding.tabLayout.addOnTabSelectedListener(object : com.google.android.material.tabs.TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: com.google.android.material.tabs.TabLayout.Tab?) {
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

            override fun onTabUnselected(tab: com.google.android.material.tabs.TabLayout.Tab?) {}
            override fun onTabReselected(tab: com.google.android.material.tabs.TabLayout.Tab?) {}
        })

        // DataStore에서 데이터 가져오기
        lifecycleScope.launch {
            val productList = productStorage.getProducts().first().toMutableList()

            val adapter = ProductAdapter(productList) { position, product ->
                lifecycleScope.launch {
                    productList[position] = product
                    productStorage.saveProducts(productList)
                }
            }

            binding.rvPurchaseProduct.adapter = adapter
            binding.rvPurchaseProduct.layoutManager = GridLayoutManager(
                requireContext(), 2
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
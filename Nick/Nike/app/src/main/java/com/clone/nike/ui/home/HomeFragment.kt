package com.clone.nike.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.clone.nike.R
import com.clone.nike.databinding.FragmentHomeBinding
import com.clone.nike.ui.purchase.GoodsData
import com.clone.nike.ui.purchase.GoodsRVAdapter
import com.clone.nike.ui.purchase.PurchaseFragmentDirections
import com.clone.nike.ui.repository.DataStoreRepository
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), NewOnclickListener {
    private lateinit var binding: FragmentHomeBinding
    val NEW_GOODS_DATA = stringPreferencesKey("new_goods_data")
    private val repository by lazy {
        DataStoreRepository(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //splash에서 title 받아오기
        val title = requireActivity().intent.getStringExtra("title")

        //뒤로가기 버튼 인식 콜백
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)

        lifecycleScope.launch {
            repository.getGoodsInfo(NEW_GOODS_DATA).collect { goodsDataString ->
                val goodsDataList = repository.jsonToGson(goodsDataString)
                updateRV(goodsDataList, title)
            }
        }
    }

    // 뒤로가기 두번 클릭 시 종료
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        var pressedTime:Long = 0;
        override fun handleOnBackPressed() {
            if(System.currentTimeMillis() - pressedTime >= 2000) {
                pressedTime = System.currentTimeMillis()
                Toast.makeText(requireContext(), "뒤로가기 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
            }
            else {
                requireActivity().finishAffinity()
            }
        }

    }

    //RV delegate pattern
    override fun newGoodsOnClickListener(newGoods: GoodsData) {
        //safe Args
        val action = HomeFragmentDirections.actionHomeToDetail(
            goodsInfo = newGoods
        )
        findNavController().navigate(action)
    }

    fun updateRV(goodsDataList: MutableList<GoodsData>, title: String?) {//adapter 연결
        val adapter = HomeRVAdapter(goodsDataList, title, this)
        binding.homeViewRV.adapter = adapter
        binding.homeViewRV.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }
}
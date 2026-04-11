package com.example.nike.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nike.ProductData
import com.example.nike.R
import com.example.nike.adapter.HomeMainAdapter
import com.example.nike.adapter.ProductAdapter
import com.example.nike.databinding.FragmentHomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. 더미 데이터 준비
        val productDataList = mutableListOf<ProductData>()
        productDataList.add(ProductData("Air Jordan XXXVI", "US$185", R.drawable.air_jordan))
        productDataList.add(ProductData("Air Jordan XXXVI", "US$185", R.drawable.air_jordan))
        productDataList.add(ProductData("Air Jordan XXXVI", "US$185", R.drawable.air_jordan))
        // (테스트하려면 여기 데이터를 여러 개 더 넣어봐!)

        // 2. 어댑터 연결 (ProductAdapter가 아니라 HomeMainAdapter를 쓴다!)
        val mainAdapter = HomeMainAdapter(productDataList, onVisitClicked = { product ->
            // 클릭 이벤트 처리 (나중에 구현)
        })

        // 3. 리사이클러뷰 설정
        // 주의: 이제 home_product_rv가 아니라 fragment_home.xml에 있는 home_main_rv를 쓴다!
        binding.homeMainRv.apply {
            this.adapter = mainAdapter
            // 부모는 세로 스크롤이니까 그냥 LinearLayoutManager
            this.layoutManager = LinearLayoutManager(requireContext())
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
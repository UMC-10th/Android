package com.clone.nike

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.clone.nike.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

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

        val title = requireActivity().intent.getStringExtra("title")
        binding.homeTitleTV.text = title

        //뒤로가기 버튼 인식 콜백
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)
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
}
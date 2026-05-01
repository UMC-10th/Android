package com.example.umc10th_android_week4

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.umc10th_android_week4.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 프로필 수정 버튼 클릭 (기존 기능 유지)
        binding.btnEditProfile.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProfileEditFragment())
                .addToBackStack(null)
                .commit()
        }
        // 프로필 화면 4개 메뉴 클릭 이벤트
        binding.menuOrder.setOnClickListener {
            Toast.makeText(requireContext(), "주문 페이지", Toast.LENGTH_SHORT).show()
        }

        binding.menuPass.setOnClickListener {
            Toast.makeText(requireContext(), "패스 페이지", Toast.LENGTH_SHORT).show()
        }

        binding.menuEvent.setOnClickListener {
            Toast.makeText(requireContext(), "이벤트 페이지", Toast.LENGTH_SHORT).show()
        }

        binding.menuSettings.setOnClickListener {
            Toast.makeText(requireContext(), "설정 페이지", Toast.LENGTH_SHORT).show()
        }

        // 나이키 멤버 혜택 클릭 이벤트
        binding.menuMemberBenefit.setOnClickListener {
            Toast.makeText(requireContext(), "나이키 멤버 혜택 페이지", Toast.LENGTH_SHORT).show()
        }
        // API 호출
        loadUserProfile()
        loadFollowingList()
    }

    // 1번 유저 정보 불러오기
    private fun loadUserProfile() {
        lifecycleScope.launch {
            try {
                val response = ApiClient.userService.getUser(
                    apiKey = ApiClient.API_KEY,
                    userId = 1
                )

                val user = response.data

                // 닉네임 표시 (first_name + last_name)
                binding.tvNickname.text = "${user.firstName} ${user.lastName}"

                // 프로필 이미지 표시 (Glide 사용)
                Glide.with(requireContext())
                    .load(user.avatar)
                    .into(binding.ivProfile)

                Log.d("ProfileFragment", "유저 정보 로드 성공: ${user.firstName}")

            } catch (e: Exception) {
                Log.e("ProfileFragment", "유저 정보 로드 실패: ${e.message}")
                Toast.makeText(requireContext(), "유저 정보 로드 실패", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 팔로잉 리스트 불러오기
    private fun loadFollowingList() {
        lifecycleScope.launch {
            try {
                val response = ApiClient.userService.getUsers(
                    apiKey = ApiClient.API_KEY
                )

                val userList = response.data

                // 팔로잉 개수 표시
                binding.tvFollowingTitle.text = "팔로잉 (${userList.size})"

                // RecyclerView 연결
                val adapter = FollowingAdapter(userList)
                binding.rvFollowing.adapter = adapter
                binding.rvFollowing.layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false
                )

                Log.d("ProfileFragment", "팔로잉 리스트 로드 성공: ${userList.size}명")

            } catch (e: Exception) {
                Log.e("ProfileFragment", "팔로잉 리스트 로드 실패: ${e.message}")
                Toast.makeText(requireContext(), "팔로잉 리스트 로드 실패", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
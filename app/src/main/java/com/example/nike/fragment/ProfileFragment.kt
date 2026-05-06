package com.example.nike.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.nike.ApiClient
import com.example.nike.adapter.FollowingAdapter
import com.example.nike.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 코루틴을 통해 비동기로 API 호출
        viewLifecycleOwner.lifecycleScope.launch {
            loadUserProfile()
            loadFollowingList()
        }
    }

    private suspend fun loadUserProfile() {
        try {
            // 미션 1: 1번 유저 정보 가져오기 (ApiClient를 통해 통신)
            val response = ApiClient.reqResService.getUser(1)

            if (response.isSuccessful) {
                val userData = response.body()?.data
                if (userData != null) {
                    // 이름 세팅
                    binding.userNicknameTv.text = "${userData.firstName} ${userData.lastName}"

                    // 프로필 이미지 세팅 (Glide 사용)
                    Glide.with(requireContext())
                        .load(userData.avatar)
                        .into(binding.profileImageIv)
                }
            } else {
                Log.e("ProfileFragment", "유저 정보 로드 실패: ${response.code()}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private suspend fun loadFollowingList() {
        try {
            // 미션 2: 팔로잉 리스트(1페이지) 가져오기
            val response = ApiClient.reqResService.getUserList(1)

            if (response.isSuccessful) {
                val userList = response.body()?.data
                if (userList != null) {
                    // avatar URL만 추출해서 리스트 만들기
                    val avatarList = userList.map { it.avatar }

                    // 리사이클러뷰 세팅
                    setupRecyclerView(avatarList)
                }
            } else {
                Log.e("ProfileFragment", "팔로잉 리스트 로드 실패: ${response.code()}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setupRecyclerView(avatars: List<String>) {
        binding.rvFollowing.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = FollowingAdapter(avatars) // 우리가 만든 어댑터 장착!
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
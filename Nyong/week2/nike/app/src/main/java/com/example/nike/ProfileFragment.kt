package com.example.nike

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.nike.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    // 메모리 누수를 막기 위한 임시 바인딩
    private var _binding: FragmentProfileBinding? = null

    // 실제로 편하게 사용할 바인딩
    private val binding get() = _binding!!

    // Hilt가 ProfileViewModel을 자동으로 생성해서 연결해줌
    private val viewModel: ProfileViewModel by viewModels()

    // 팔로잉 RecyclerView 어댑터
    private lateinit var followingAdapter: FollowingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    // 화면이 완전히 만들어진 직후 실행되는 함수
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 팔로잉 RecyclerView 세팅
        setupFollowingRecyclerView()

        // ViewModel의 StateFlow를 관찰해서 화면 업데이트
        observeUiState()

        // 프로필 데이터 불러오기 요청
        viewModel.loadProfile()
    }

    private fun setupFollowingRecyclerView() {
        followingAdapter = FollowingAdapter(emptyList())

        binding.rvFollowing.adapter = followingAdapter
        binding.rvFollowing.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->

                    // 1번 유저 정보가 있으면 프로필 이름/이미지 표시
                    state.user?.let { user ->
                        val fullName = "${user.firstName} ${user.lastName}"
                        binding.tvProfileName.text = fullName

                        Glide.with(requireContext())
                            .load(user.avatar)
                            .circleCrop()
                            .into(binding.ivProfileAvatar)
                    }

                    // 팔로잉 목록 표시
                    followingAdapter.updateUsers(state.followingUsers)
                    binding.tvFollowingTitle.text = "팔로잉 (${state.followingUsers.size})"

                    // 에러 메시지가 있으면 Toast 표시
                    state.errorMessage?.let { message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Fragment View가 사라질 때 binding 해제
        _binding = null
    }
}
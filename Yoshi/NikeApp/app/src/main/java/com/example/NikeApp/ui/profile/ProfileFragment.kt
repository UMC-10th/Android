package com.example.NikeApp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.NikeApp.R
import com.example.NikeApp.databinding.FragmentProfileBinding
import com.example.NikeApp.ui.adapter.FollowingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels()

    private val followingAdapter by lazy { FollowingAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 프로필 수정 버튼
        binding.btnEditProfile.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProfileEditFragment())
                .addToBackStack(null)
                .commit()
        }

        // 메뉴 클릭 (UI 로직만 — 단순 Toast)
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
        binding.menuMemberBenefit.setOnClickListener {
            Toast.makeText(requireContext(), "나이키 멤버 혜택 페이지", Toast.LENGTH_SHORT).show()
        }

        // RecyclerView 설정
        binding.rvFollowing.adapter = followingAdapter
        binding.rvFollowing.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )

        // ViewModel 상태 구독
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                state.user?.let { user ->
                    binding.tvNickname.text = "${user.firstName} ${user.lastName}"
                    Glide.with(requireContext())
                        .load(user.avatar)
                        .into(binding.ivProfile)
                }

                binding.tvFollowingTitle.text = "팔로잉 (${state.followings.size})"
                followingAdapter.submitList(state.followings)

                state.errorMessage?.let { msg ->
                    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
                    viewModel.consumeErrorMessage()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

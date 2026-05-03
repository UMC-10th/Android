package com.example.nike

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
import com.example.nike.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    // 메모리 누수를 막기 위한 임시 바인딩
    private var _binding: FragmentProfileBinding? = null

    // 실제로 편하게 사용할 바인딩
    private val binding get() = _binding!!

    // 팔로잉 RecyclerView 어댑터
    private lateinit var followingAdapter: FollowingAdapter

    // ReqRes에서 발급받은 API Key
    private val REQRES_API_KEY = "reqres_64014f50bd074eaa96984e53bfa9db8c"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // fragment_profile.xml과 연결
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    // 화면이 완전히 만들어진 직후 실행되는 함수
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 팔로잉 RecyclerView 세팅
        setupFollowingRecyclerView()

        // ReqRes API로 1번 유저 정보를 가져와 프로필에 표시
        fetchUserProfile()

        // ReqRes API로 유저 리스트를 가져와 팔로잉 RecyclerView에 표시
        fetchFollowingUsers()
    }

    private fun setupFollowingRecyclerView() {
        // 처음에는 빈 리스트로 어댑터 생성
        followingAdapter = FollowingAdapter(emptyList())

        binding.rvFollowing.adapter = followingAdapter
        binding.rvFollowing.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
    }

    private fun fetchUserProfile() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                // 1번 유저 정보 요청
                val response = ReqresClient.apiService.getUser(
                    apiKey = REQRES_API_KEY,
                    userId = 1
                )

                if (response.isSuccessful) {
                    val user = response.body()?.data

                    if (user != null) {
                        // firstName + lastName 형태로 닉네임 표시
                        val fullName = "${user.firstName} ${user.lastName}"
                        binding.tvProfileName.text = fullName

                        // 서버에서 받은 avatar URL을 프로필 이미지에 표시
                        Glide.with(requireContext())
                            .load(user.avatar)
                            .circleCrop()
                            .into(binding.ivProfileAvatar)

                        Log.d("REQRES", "프로필 로드 성공: $fullName")
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "유저 정보가 비어 있습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Log.d(
                        "REQRES",
                        "프로필 실패: ${response.code()} ${response.errorBody()?.string()}"
                    )
                    Toast.makeText(
                        requireContext(),
                        "프로필 정보를 가져오지 못했습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                Log.d("REQRES", "프로필 통신 오류: ${e.message}")
                Toast.makeText(
                    requireContext(),
                    "네트워크 오류: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun fetchFollowingUsers() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                // 유저 리스트 요청
                val response = ReqresClient.apiService.getUsers(
                    apiKey = REQRES_API_KEY,
                    page = 1
                )

                if (response.isSuccessful) {
                    val users = response.body()?.data ?: emptyList()

                    // 팔로잉 6명 보여주기
                    val followingUsers = users.take(6)

                    // RecyclerView 데이터 갱신
                    followingAdapter.updateUsers(followingUsers)

                    // 팔로잉 숫자도 실제 개수에 맞게 표시
                    binding.tvFollowingTitle.text = "팔로잉 (${followingUsers.size})"

                    Log.d("REQRES", "팔로잉 리스트 로드 성공: ${followingUsers.size}명")
                } else {
                    Log.d(
                        "REQRES",
                        "팔로잉 실패: ${response.code()} ${response.errorBody()?.string()}"
                    )
                    Toast.makeText(
                        requireContext(),
                        "팔로잉 목록을 가져오지 못했습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                Log.d("REQRES", "팔로잉 통신 오류: ${e.message}")
                Toast.makeText(
                    requireContext(),
                    "네트워크 오류: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Fragment View가 사라질 때 binding 해제
        _binding = null
    }
}
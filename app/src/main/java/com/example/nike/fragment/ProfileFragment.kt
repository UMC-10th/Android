package com.example.nike.fragment

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nike.databinding.FragmentProfileBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val apiKey = "reqres_5193c564727d460caf02211006d13c9b"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                // [미션 1] 1번 유저 정보 가져오기
                val userRes = fetchWithHeader("https://reqres.in/api/users/1")
                val userJson = JSONObject(userRes).getJSONObject("data")

                binding.userNicknameTv.text = "${userJson.getString("first_name")} ${userJson.getString("last_name")}"

                // 프로필 이미지 세팅
                val myBitmap = fetchBitmap(userJson.getString("avatar"))
                binding.profileImageIv.setImageBitmap(myBitmap)

                // [미션 2] 팔로잉 리스트(2번째 사진처럼 구현)
                val listRes = fetchWithHeader("https://reqres.in/api/users?page=1")
                val usersArray = JSONObject(listRes).getJSONArray("data")
                val avatarList = mutableListOf<String>()
                for (i in 0 until usersArray.length()) {
                    avatarList.add(usersArray.getJSONObject(i).getString("avatar"))
                }

                // 리사이클러뷰 알맹이 채우기
                setupRecyclerView(avatarList)

            } catch (e: Exception) { e.printStackTrace() }
        }
    }

    private fun setupRecyclerView(avatars: List<String>) {
        binding.rvFollowing.apply {
            // 가로로 넘기게 설정
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
                override fun onCreateViewHolder(p: ViewGroup, t: Int): RecyclerView.ViewHolder {
                    // 아이템용 ImageView 즉석 제조
                    val iv = ImageView(p.context).apply {
                        layoutParams = ViewGroup.MarginLayoutParams(250, 250).apply {
                            setMargins(0, 0, 30, 0) // 간격 조절
                        }
                        scaleType = ImageView.ScaleType.CENTER_CROP
                    }
                    return object : RecyclerView.ViewHolder(iv) {}
                }

                override fun onBindViewHolder(h: RecyclerView.ViewHolder, pos: Int) {
                    val iv = h.itemView as ImageView
                    viewLifecycleOwner.lifecycleScope.launch {
                        val b = fetchBitmap(avatars[pos])
                        iv.setImageBitmap(b)
                    }
                }
                override fun getItemCount(): Int = avatars.size
            }
        }
    }

    private suspend fun fetchWithHeader(u: String): String = withContext(Dispatchers.IO) {
        val conn = URL(u).openConnection() as HttpURLConnection
        conn.setRequestProperty("x-api-key", apiKey)
        conn.inputStream.bufferedReader().use { it.readText() }
    }

    private suspend fun fetchBitmap(url: String) = withContext(Dispatchers.IO) {
        val conn = URL(url).openConnection() as HttpURLConnection
        BitmapFactory.decodeStream(conn.inputStream)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
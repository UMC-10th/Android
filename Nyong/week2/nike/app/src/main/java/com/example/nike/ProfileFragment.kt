package com.example.nike

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    // 포스트잇(프래그먼트)의 화면을 그려주는 핵심 마법 함수입니다!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // fragment_profile.xml 이라는 도면을 가져와서 화면에 부풀려라(inflate)!
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
}
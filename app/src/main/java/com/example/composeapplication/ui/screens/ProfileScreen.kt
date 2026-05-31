package com.example.composeapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.composeapplication.data.model.UserData
import com.example.composeapplication.network.RetrofitClient

@Composable
fun ProfileScreen() {
    var user by remember { mutableStateOf<UserData?>(null) }
    var followingList by remember { mutableStateOf<List<UserData>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        try {
            val userResponse = RetrofitClient.service.getUser(1)
            if (userResponse.isSuccessful) {
                user = userResponse.body()?.data
            }

            val listResponse = RetrofitClient.service.getUserList(1)
            if (listResponse.isSuccessful) {
                followingList = listResponse.body()?.data ?: emptyList()
            }
        } catch (e: Exception) {
            // 에러 무시
        } finally {
            isLoading = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(top = 80.dp))
        } else {
            user?.let { currentUser ->
                AsyncImage(
                    model = currentUser.avatar,
                    contentDescription = "프로필 이미지",
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .size(100.dp)
                        .clip(CircleShape)
                )
                Text(
                    text = "${currentUser.firstName} ${currentUser.lastName}",
                    modifier = Modifier.padding(top = 16.dp),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            } ?: run {
                Text(
                    text = "유저 정보를 불러올 수 없습니다.",
                    modifier = Modifier.padding(top = 40.dp),
                    color = Color.Red
                )
            }

            OutlinedButton(
                onClick = { },
                modifier = Modifier.padding(top = 24.dp),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text("프로필 수정", color = Color.Black)
            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "팔로잉 (${followingList.size})",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 8.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            if (followingList.isNotEmpty()) {
                val pagerState = rememberPagerState(pageCount = { followingList.size })

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp),
                    pageSpacing = 12.dp,
                    contentPadding = PaddingValues(horizontal = 32.dp)
                ) { page ->
                    val followUser = followingList[page]
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFEFEFEF)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            AsyncImage(
                                model = followUser.avatar,
                                contentDescription = "${followUser.firstName} 프로필",
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "${followUser.firstName} ${followUser.lastName}",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            } else {
                Text(
                    text = "팔로잉 목록이 없습니다",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 20.dp)
                )
            }
        }
    }
}
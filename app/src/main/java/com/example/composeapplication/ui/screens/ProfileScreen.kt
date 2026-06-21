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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.composeapplication.data.model.UserData

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        is ProfileUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is ProfileUiState.Success -> {
            ProfileContent(
                user = state.user,
                followingList = state.followingList
            )
        }

        is ProfileUiState.Error -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = state.message,
                    color = Color.Red,
                    fontSize = 16.sp
                )
                Button(
                    onClick = { viewModel.fetchProfile() },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("다시 시도")
                }
            }
        }
    }
}

@Composable
private fun ProfileContent(
    user: UserData,
    followingList: List<UserData>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = user.avatar,
            contentDescription = "프로필 이미지",
            modifier = Modifier
                .padding(top = 40.dp)
                .size(100.dp)
                .clip(CircleShape)
        )
        Text(
            text = "${user.firstName} ${user.lastName}",
            modifier = Modifier.padding(top = 16.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

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

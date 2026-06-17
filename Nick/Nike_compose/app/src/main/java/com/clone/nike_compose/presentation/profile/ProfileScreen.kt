package com.clone.nike_compose.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.clone.nike_compose.R

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    MaterialTheme {
        ProfileScreenContent(
            profileName = "George Bluth",
            profileAvatar = "https://reqres.in/img/faces/1-image.jpg",
            following = listOf(
                "https://reqres.in/img/faces/2-image.jpg",
                "https://reqres.in/img/faces/3-image.jpg",
                "https://reqres.in/img/faces/4-image.jpg",
                "https://reqres.in/img/faces/5-image.jpg"
            )
        )
    }
}

@Composable
fun ProfileScreen() {
    val vm: ProfileViewModel = viewModel()
    val state by vm.uiState.collectAsState()

    val profileName = state.profileUser?.let { "${it.first_name} ${it.last_name}" } ?: "닉네임"
    val profileAvatar = state.profileUser?.avatar
    val followingAvatars = state.following.map { it.avatar }

    ProfileScreenContent(
        profileName = profileName,
        profileAvatar = profileAvatar,
        following = followingAvatars
    )
}

@Composable
private fun ProfileScreenContent(
    profileName: String,
    profileAvatar: String?,
    following: List<String>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3F3F3))
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color.White
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(vertical = 20.dp)
            ) {
                if (!profileAvatar.isNullOrBlank()) {
                    AsyncImage(
                        model = profileAvatar,
                        contentDescription = "프로필",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(86.dp)
                            .clip(CircleShape)
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(86.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFD0D0D0))
                    )
                }

                Spacer(Modifier.height(14.dp))
                Text(
                    text = profileName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(14.dp))

                OutlinedButton(
                    onClick = {},
                    shape = RoundedCornerShape(28.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
                ) {
                    Text("프로필 수정")
                }

                Spacer(Modifier.height(18.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    MidIcon("주문", R.drawable.ic_archive)
                    MidIcon("패스", R.drawable.ic_identificationcard)
                    MidIcon("이벤트", R.drawable.ic_calendarblank)
                    MidIcon("설정", R.drawable.ic_gear)
                }
            }
        }

        Spacer(Modifier.height(8.dp))

        Surface(color = Color.White, modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("나이키 멤버 혜택", fontWeight = FontWeight.SemiBold)
                    Text("0개 사용 가능", color = Color.Gray, style = MaterialTheme.typography.bodySmall)
                }
                Spacer(Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "right arrow",
                    modifier = Modifier.size(15.dp)
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        Surface(color = Color.White, modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(vertical = 14.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("팔로잉 (${following.size})", fontWeight = FontWeight.SemiBold)
                    Spacer(Modifier.weight(1f))
                    Text("편집", color = Color.Gray, style = MaterialTheme.typography.bodySmall)
                }

                Spacer(Modifier.height(12.dp))

                val pagerState = rememberPagerState(
                    initialPage = 0,
                    pageCount = { following.size }
                )

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    pageSpacing = 8.dp,
                    pageSize = PageSize.Fixed(82.dp)
                ) { page ->
                    val avatarUrl = following[page]
                    AsyncImage(
                        model = avatarUrl,
                        contentDescription = "following",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(82.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(Color(0xFFD0D0D0))
                    )
                }


            }
        }

        Surface(color = Color.White, modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(vertical = 14.dp),
                verticalArrangement = Arrangement.Bottom) {
                Text(
                    text = "회원 가입일: 2025년 9월",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
private fun MidIcon(label: String, iconRes: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            tint = Color(0xFF9A9A9A),
            modifier = Modifier.size(20.dp)
        )
        Spacer(Modifier.height(6.dp))
        Text(label, style = MaterialTheme.typography.bodySmall)
    }
}


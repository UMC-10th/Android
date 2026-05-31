package com.example.nikecompose.feature.profile.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CardTravel
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.nikecompose.data.dto.response.ReqresUserDto

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Black
                )
            }

            uiState.errorMessage != null -> {
                Text(
                    text = uiState.errorMessage ?: "알 수 없는 오류가 발생했습니다.",
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Red,
                    textAlign = TextAlign.Center
                )
            }

            else -> {
                ProfileContent(
                    profileUser = uiState.profileUser,
                    followingUsers = uiState.followingUsers
                )
            }
        }
    }
}

@Composable
fun ProfileContent(
    profileUser: ReqresUserDto?,
    followingUsers: List<ReqresUserDto>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.height(44.dp))

        ProfileHeader(profileUser = profileUser)

        Spacer(modifier = Modifier.height(28.dp))

        ProfileMenuRow()

        Spacer(modifier = Modifier.height(20.dp))

        HorizontalDivider(
            thickness = 8.dp,
            color = Color(0xFFF6F6F6)
        )

        MemberBenefitSection()

        HorizontalDivider(
            thickness = 8.dp,
            color = Color(0xFFF6F6F6)
        )

        FollowingSection(
            followingUsers = followingUsers
        )

        Spacer(modifier = Modifier.weight(1f))

        JoinDateSection()
    }
}

@Composable
fun ProfileHeader(
    profileUser: ReqresUserDto?
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = profileUser?.avatar,
            contentDescription = "프로필 이미지",
            modifier = Modifier
                .size(92.dp)
                .clip(CircleShape)
                .background(Color(0xFFD9D9D9)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = if (profileUser != null) {
                "${profileUser.firstName} ${profileUser.lastName}"
            } else {
                "닉네임"
            },
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(22.dp))

        Button(
            onClick = { },
            modifier = Modifier
                .width(160.dp)
                .height(46.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            border = ButtonDefaults.outlinedButtonBorder
        ) {
            Text(
                text = "프로필 수정",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ProfileMenuRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfileMenuItem(
            icon = Icons.Outlined.CardTravel,
            label = "주문"
        )

        VerticalMenuDivider()

        ProfileMenuItem(
            icon = Icons.Outlined.ConfirmationNumber,
            label = "패스"
        )

        VerticalMenuDivider()

        ProfileMenuItem(
            icon = Icons.Outlined.Event,
            label = "이벤트"
        )

        VerticalMenuDivider()

        ProfileMenuItem(
            icon = Icons.Outlined.Settings,
            label = "설정"
        )
    }
}

@Composable
fun ProfileMenuItem(
    icon: ImageVector,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color(0xFFB5B5B5),
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = label,
            fontSize = 11.sp,
            color = Color.Black
        )
    }
}

@Composable
fun VerticalMenuDivider() {
    Box(
        modifier = Modifier
            .height(32.dp)
            .width(1.dp)
            .background(Color(0xFFE0E0E0))
    )
}

@Composable
fun MemberBenefitSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(84.dp)
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "나이키 멤버 혜택",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "0개 사용 가능",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }

        Icon(
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = "더보기",
            tint = Color.Black,
            modifier = Modifier.size(22.dp)
        )
    }
}

@Composable
fun FollowingSection(
    followingUsers: List<ReqresUserDto>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(top = 22.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "팔로잉 (${followingUsers.size})",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "편집",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (followingUsers.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "팔로잉 목록이 없습니다.",
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }
        } else {
            FollowingPager(users = followingUsers)
        }
    }
}

@Composable
fun FollowingPager(
    users: List<ReqresUserDto>
) {
    val pages = users.chunked(3)

    val pagerState = rememberPagerState(
        pageCount = { pages.size }
    )

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        contentPadding = PaddingValues(horizontal = 24.dp),
        pageSpacing = 12.dp
    ) { page ->
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            pages[page].forEach { user ->
                FollowingUserCard(
                    user = user,
                    modifier = Modifier.weight(1f)
                )
            }

            repeat(3 - pages[page].size) {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun FollowingUserCard(
    user: ReqresUserDto,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        AsyncImage(
            model = user.avatar,
            contentDescription = "${user.firstName} ${user.lastName}",
            modifier = Modifier
                .fillMaxWidth()
                .height(84.dp)
                .background(Color(0xFFD9D9D9)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = user.firstName,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            maxLines = 1
        )
    }
}

@Composable
fun JoinDateSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(42.dp)
            .background(Color(0xFFF7F7F7)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "회원 가입일: 2025년 9월",
            fontSize = 11.sp,
            color = Color.Gray
        )
    }
}
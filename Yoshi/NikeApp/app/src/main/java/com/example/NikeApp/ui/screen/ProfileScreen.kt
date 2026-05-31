package com.example.NikeApp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.NikeApp.R
import com.example.NikeApp.data.UserRepository
import com.example.NikeApp.model.User
import com.example.NikeApp.ui.theme.NikeAppTheme
import com.example.NikeApp.ui.theme.NikeBlack
import com.example.NikeApp.ui.theme.NikeGray
import com.example.NikeApp.ui.theme.NikeLightGray

/**
 * 프로필(마이페이지) 화면의 데이터 상태
 *  - 워크북의 LaunchedEffect 비동기 패턴으로 ReqRes 에서 데이터를 받아옵니다.
 */
private sealed interface ProfileUiState {
    data object Loading : ProfileUiState
    data class Success(val me: User, val following: List<User>) : ProfileUiState
    data class Error(val message: String) : ProfileUiState
}

/**
 * 마이페이지 화면
 *  - userId 1번 사용자의 이미지/닉네임을 ReqRes 에서 받아와 표시
 *  - 팔로잉 리스트는 HorizontalPager 로 구현
 */
@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    val repository = remember { UserRepository() }

    var uiState by remember { mutableStateOf<ProfileUiState>(ProfileUiState.Loading) }
    // 에러 시 "다시 시도"를 누르면 key 가 바뀌어 LaunchedEffect 가 재실행됩니다.
    var retryKey by remember { mutableIntStateOf(0) }

    LaunchedEffect(retryKey) {
        uiState = ProfileUiState.Loading
        uiState = try {
            val me = repository.getMyProfile()
            val following = repository.getFollowing()
            ProfileUiState.Success(me = me, following = following)
        } catch (e: Exception) {
            ProfileUiState.Error(e.message ?: "데이터를 불러오지 못했습니다.")
        }
    }

    when (val state = uiState) {
        ProfileUiState.Loading -> LoadingView(modifier)
        is ProfileUiState.Error -> ErrorView(
            message = state.message,
            onRetry = { retryKey++ },
            modifier = modifier,
        )
        is ProfileUiState.Success -> ProfileContent(
            me = state.me,
            following = state.following,
            modifier = modifier,
        )
    }
}

@Composable
private fun LoadingView(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = NikeBlack)
    }
}

@Composable
private fun ErrorView(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "오류가 발생했어요", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(Modifier.height(8.dp))
        Text(text = message, color = NikeGray, fontSize = 14.sp)
        Spacer(Modifier.height(16.dp))
        Button(onClick = onRetry) { Text("다시 시도") }
    }
}

@Composable
private fun ProfileContent(
    me: User,
    following: List<User>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(24.dp))

        // 프로필 이미지 (ReqRes avatar) — 원형
        AsyncImage(
            model = me.avatarUrl,
            contentDescription = "${me.nickname} 프로필 이미지",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(NikeLightGray),
        )

        Spacer(Modifier.height(16.dp))

        // 닉네임 (first_name + last_name)
        Text(
            text = me.nickname,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = NikeBlack,
        )

        Spacer(Modifier.height(16.dp))

        OutlinedButton(
            onClick = { /* 프로필 수정 (미구현) */ },
            shape = RoundedCornerShape(50),
            modifier = Modifier.padding(horizontal = 80.dp),
        ) {
            Text(
                text = "프로필 수정",
                color = NikeBlack,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 24.dp),
            )
        }

        Spacer(Modifier.height(24.dp))

        // 빠른 메뉴: 주문 / 패스 / 이벤트 / 설정
        QuickActionRow()

        Spacer(Modifier.height(16.dp))
        HorizontalDivider(thickness = 8.dp, color = NikeLightGray)

        // 나이키 멤버 혜택
        MemberBenefitRow()

        HorizontalDivider(thickness = 8.dp, color = NikeLightGray)

        Spacer(Modifier.height(24.dp))

        // 팔로잉 리스트 (HorizontalPager)
        FollowingSection(following = following)

        Spacer(Modifier.height(40.dp))

        Text(
            text = "회원 가입일: 2025년 9월",
            color = NikeGray,
            fontSize = 13.sp,
        )

        Spacer(Modifier.height(24.dp))
    }
}

/** 주문 / 패스 / 이벤트 / 설정 — 제공된 drawable(아이콘+라벨 일체형)을 그대로 사용 */
@Composable
private fun QuickActionRow(modifier: Modifier = Modifier) {
    val items = listOf(
        R.drawable.menu_order,
        R.drawable.menu_pass,
        R.drawable.menu_event,
        R.drawable.menu_settings,
    )
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items.forEachIndexed { index, drawable ->
            Icon(
                painter = painterResource(id = drawable),
                contentDescription = null,
                tint = NikeBlack,
                modifier = Modifier
                    .weight(1f)
                    .height(46.dp),
            )
            if (index != items.lastIndex) {
                VerticalDivider(
                    modifier = Modifier.height(36.dp),
                    color = NikeLightGray,
                )
            }
        }
    }
}

/** 나이키 멤버 혜택 — 클릭 가능한 행 */
@Composable
private fun MemberBenefitRow(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "나이키 멤버 혜택",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = NikeBlack,
            )
            Spacer(Modifier.height(4.dp))
            Text(text = "0개 사용 가능", fontSize = 13.sp, color = NikeGray)
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = null,
            tint = NikeBlack,
        )
    }
}

/** 팔로잉 리스트 — 워크북 요구사항대로 HorizontalPager 로 구현 */
@Composable
private fun FollowingSection(
    following: List<User>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "팔로잉 (${following.size})",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = NikeBlack,
            )
            Text(text = "편집", fontSize = 14.sp, color = NikeGray)
        }

        Spacer(Modifier.height(16.dp))

        if (following.isEmpty()) {
            Text(
                text = "팔로잉한 사용자가 없습니다.",
                color = NikeGray,
                modifier = Modifier.padding(horizontal = 20.dp),
            )
            return@Column
        }

        val pagerState = rememberPagerState(pageCount = { following.size })
        HorizontalPager(
            state = pagerState,
            pageSize = PageSize.Fixed(150.dp),
            pageSpacing = 12.dp,
            contentPadding = PaddingValues(horizontal = 20.dp),
        ) { page ->
            AsyncImage(
                model = following[page].avatarUrl,
                contentDescription = "${following[page].nickname} 이미지",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(NikeLightGray),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileContentPreview() {
    val sampleMe = User(1, "George Bluth", "")
    val sampleFollowing = listOf(
        User(2, "Janet Weaver", ""),
        User(3, "Emma Wong", ""),
        User(4, "Eve Holt", ""),
    )
    NikeAppTheme {
        ProfileContent(me = sampleMe, following = sampleFollowing)
    }
}

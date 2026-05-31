package com.example.nike.presentation.profile

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.nike.R
import com.example.nike.core.designsystem.theme.NikeTheme
import com.example.nike.domain.model.profile.User

@Composable
fun ProfileRoute(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = viewModel(),
) {
    val users by viewModel.users.collectAsStateWithLifecycle()

    val me = users.find { it.id == 1 }
    val following = users.filter { it.id != 1 }

    ProfileScreen(
        me = me,
        followingList = following,
        modifier = modifier,
    )
}

@Composable
private fun ProfileScreen(
    me: User?,
    followingList: List<User>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        item {
            MyProfileSection(me = me)
        }

        item {
            HorizontalDivider(color = Color(0xFFF6F6F6), thickness = 8.dp)
        }

        item {
            MemberBenefitSection()
        }

        item {
            HorizontalDivider(color = Color(0xFFF6F6F6), thickness = 8.dp)
        }

        item {
            FollowingListSection(followingList = followingList)
        }

        item {
            Footer()
        }
    }
}

@Composable
private fun MyProfileSection(
    me: User?,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 21.dp,
                bottom = 25.dp,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = me?.avatar,
            contentDescription = null,
            modifier = Modifier
                .size(84.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.img_profile_placeholder),
            error = painterResource(R.drawable.img_profile_placeholder)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "${me?.firstName} ${me?.lastName}",
            color = Color.Black,
            fontSize = 20.sp,
        )

        Spacer(modifier = Modifier.height(30.dp))

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .border(
                    width = 1.dp,
                    color = Color(0xFFE4E4E4),
                    shape = RoundedCornerShape(50),
                )
                .padding(
                    vertical = 16.dp,
                    horizontal = 50.dp,
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(R.string.profile_edit),
                color = Color.Black,
                fontSize = 16.sp,
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        MenuRow()
    }
}

@Composable
private fun MenuRow(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MenuItem(
            icon = R.drawable.ic_profile_order,
            label = R.string.profile_menu_order,
        )

        VerticalDivider(
            color = Color(0xFFCDCDCD),
            thickness = 1.dp,
            modifier = Modifier
                .height(31.dp)
        )

        MenuItem(
            icon = R.drawable.ic_profile_pass,
            label = R.string.profile_menu_pass,
        )

        VerticalDivider(
            color = Color(0xFFCDCDCD),
            thickness = 1.dp,
            modifier = Modifier
                .height(30.dp)
        )

        MenuItem(
            icon = R.drawable.ic_profile_event,
            label = R.string.profile_menu_event,
        )

        VerticalDivider(
            color = Color(0xFFCDCDCD),
            thickness = 1.dp,
            modifier = Modifier
                .height(30.dp)
        )

        MenuItem(
            icon = R.drawable.ic_profile_setting,
            label = R.string.profile_menu_setting,
        )
    }
}

@Composable
private fun MenuItem(
    @DrawableRes icon: Int,
    @StringRes label: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = null,
            tint = Color.Unspecified,
        )

        Text(
            text = stringResource(label),
            color = Color.Black,
            fontSize = 12.sp,
        )
    }
}

@Composable
private fun MemberBenefitSection(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 24.dp,
                vertical = 32.dp,
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = stringResource(R.string.profile_member_benefit),
                color = Color.Black,
                fontSize = 16.sp,
            )

            Text(
                text = stringResource(R.string.profile_member_benefit_count),
                color = Color(0xFF767676),
                fontSize = 12.sp,
            )
        }

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_profile_chevron_right),
            contentDescription = null,
            tint = Color.Unspecified,
        )
    }
}

@Composable
private fun FollowingListSection(
    followingList: List<User>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 28.dp,
                bottom = 115.dp,
            ),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 24.dp
                ),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
        ) {
            Text(
                text = stringResource(R.string.profile_following_list, followingList.size),
                color = Color.Black,
                fontSize = 14.sp,
            )

            Text(
                text = stringResource(R.string.profile_following_list_edit),
                color = Color(0xFF767676),
                fontSize = 12.sp,
            )
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) {
            items(followingList) { user ->
                AsyncImage(
                    model = user.avatar,
                    contentDescription = null,
                    modifier = Modifier
                        .size(105.dp),
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}

@Composable
private fun Footer(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(Color(0xFFF6F6F6))
            .fillMaxWidth()
            .padding(
                vertical = 19.dp,
                horizontal = 79.dp,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(R.string.profile_footer),
            color = Color(0xFF767676),
            fontSize = 12.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    val dummyUsers = listOf(
        User(id = 1, email = "me@nike.com", firstName = "김", lastName = "민지", avatar = ""),
        User(id = 2, email = "a@nike.com", firstName = "김", lastName = "영희", avatar = ""),
        User(id = 3, email = "b@nike.com", firstName = "이", lastName = "서연", avatar = ""),
        User(id = 4, email = "c@nike.com", firstName = "박", lastName = "철수", avatar = ""),
    )

    val me = dummyUsers.find { it.id == 1 }
    val following = dummyUsers.filter { it.id != 1 }

    NikeTheme {
        ProfileScreen(
            me = me,
            followingList = following,
        )
    }
}
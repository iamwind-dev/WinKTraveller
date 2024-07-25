package vku.duongdlt.winktraveller.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import kotlinx.coroutines.launch
import vku.duongdlt.winktraveller.R
import vku.duongdlt.winktraveller.ViewModel.UserViewModel
import vku.duongdlt.winktraveller.component.Toggle
import vku.duongdlt.winktraveller.model.User
import vku.duongdlt.winktraveller.util.BOTTOM_NAV_SPACE

import vku.duongdlt.winktraveller.navigation.Route
import vku.duongdlt.winktraveller.navigation.Screen
import vku.duongdlt.winktraveller.ui.theme.HeliaTheme

enum class ProfileScreenContents{
    HEADER_SECTION,
    INFORMATION_USER,
    CATEGORY_SECTION,
    DESTINATION_LARGE_SECTION,
    DESTINATION_VIEW_ALL,
    DESTINATION_SMALL_SECTION,
}
@OptIn(ExperimentalComposeUiApi::class)

@Composable
fun ProfileScreen(routeState: MutableState<Route>,userViewModel: UserViewModel) {
    var user by remember { mutableStateOf<User?>(null) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        userViewModel.getCurrentUser {
            user = it
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                        .background(colorResource(id = R.color.TextColor))
                ) {
                    val (image, cameraIcon, nameText) = createRefs()

                    Image(
                        painter = painterResource(id = R.drawable.avatar_n),
                        contentDescription = "Profile Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .constrainAs(image) {
                                linkTo(start = parent.start, end = parent.end)
                                linkTo(top = parent.top, bottom = parent.bottom)
                            }
                    )

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(30.dp)
                            .background(Color.LightGray, CircleShape)
                            .constrainAs(cameraIcon) {
                                bottom.linkTo(image.bottom)
                                end.linkTo(image.end)
                            }
                    ) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.camera_icon),
                                contentDescription = "Change Picture",
                            )
                        }
                    }

                    user?.let {
                        Text(
                            text = it.user_username,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.constrainAs(nameText) {
                                top.linkTo(image.bottom, margin = 8.dp)
                                linkTo(start = parent.start, end = parent.end)
                            }
                        )
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = BOTTOM_NAV_SPACE)
                        .background(HeliaTheme.colors.white)
                ) {
                    // Nội dung của Row thứ hai
                    Column (modifier = Modifier.fillMaxWidth()){


                        SettingCategory(
                            modifier = Modifier.fillMaxWidth(),
                            icon = painterResource(id = R.drawable.ic_profile_border),
                            text = "Profile",
                            onClick = {
                                routeState.value = Route(screen = Screen.InformationScreen,prev = Screen.ProfileScreen)
                            }
                        )

                        SettingCategory(
                            modifier = Modifier.fillMaxWidth(),
                            icon = painterResource(id = R.drawable.ic_shield_done_border),
                            text = "Security",
                            onClick = { }
                        )

                        SettingCategory(
                            modifier = Modifier.fillMaxWidth(),
                            icon = painterResource(id = R.drawable.ic_profile_border),
                            text = "Profile",
                            onClick = { }
                        )

                        SettingCategory(
                            modifier = Modifier.fillMaxWidth(),
                            icon = painterResource(id = R.drawable.ic_info_circle),
                            text = "Help",
                            onClick = { }
                        )

                        SettingThemeCategory(checked = false, onCheckedChange = {})
                        
                        SettingLogoutCategory(onClick = {
                            coroutineScope.launch {
                                val isLogout = userViewModel.logoutUser()
                                if (isLogout) {
                                    routeState.value = Route(screen = Screen.LoginScreen)
                                }
                            }
                        })
                    }
                }

                // Thêm nhiều mục khác nếu cần
            }
        }

//    Surface(modifier = Modifier
//        .fillMaxWidth()
//        .padding(top = 31.dp, bottom = BOTTOM_NAV_SPACE)) {
////        VerticalScrollLayout(
////            modifier = Modifier.fillMaxSize()
////                .background(color = MaterialTheme.colorScheme.background)
////                .padding(top=31.dp),
////            ChildLayout(
////                contentType = ProfileScreenContents.HEADER_SECTION.name,
////                content = {
////                    profileHeader()
////                }
////            ),
////            ChildLayout(
////                contentType = ProfileScreenContents.INFORMATION_USER.name,
////                content = {
////                    InformationCard()
////                }
////            )
////        )
////        Text(
////            modifier = Modifier.wrapContentSize(Alignment.Center),
////            text = "Profile",
////            color = colorResource(R.color.textColor),
////            style = MaterialTheme.typography.titleLarge
////        )
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(15.dp)
//        ) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Button(
//                    onClick = { routeState.value = routeState.value.copy(
//                        screen = routeState.value.prev ?: routeState.value.screen
//                    )},
//                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
//
//                ) {
//                        Icon(
//                            tint = colorResource(id=R.color.textColor),
//                            painter = painterResource(id = R.drawable.back_icon),
//                            contentDescription = null)
//                }
//
//                Box(modifier = Modifier.weight(0.7f)) {
//                    Text(
//                        text = "Profile",
//                        color = Color.Black,
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Bold
//                    )
//                }
//
//            }
//            Spacer(modifier = Modifier.height(30.dp))
//            Row(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(colorResource(id = R.color.TextColor))
//
//            ) {
//                ConstraintLayout(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                ) {
//                    val (image, cameraIcon) = createRefs()
//                    Image(
//                        painter = painterResource(id = R.drawable.profile_image),
//                        contentDescription = "Profile Image",
//                        modifier = Modifier
//                            .clip(CircleShape)
//                            .constrainAs(image) {
//                                linkTo(start = parent.start, end = parent.end)
//                            }
//                    )
//                    Box(
//                        contentAlignment = Alignment.Center,
//                        modifier = Modifier.constrainAs(cameraIcon) {
//                            bottom.linkTo(image.bottom)
//                            end.linkTo(image.end)
//
//                        }) {
//
//                        IconButton(onClick = { /*TODO*/ }) {
//                            Icon(
//                                painter = painterResource(id = R.drawable.camera_icon),
//                                contentDescription = "Change Picture",
//                                modifier = Modifier.background(Color.LightGray),
//                            )
//                        }
//                    }
//                }
//            }
//            Spacer(modifier = Modifier.height(60.dp))
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(70.dp)
//
//                    .background(Color(0x8DB3B0B0), shape = RoundedCornerShape(10.dp))
//                    .clip(RoundedCornerShape(10.dp))
//                    .clickable {
//
//                    }
//                    .padding(5.dp),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ){
//                Icon(
//                    painter = painterResource(id = R.drawable.user_icon),
//                    contentDescription = null,
//                    modifier = Modifier.weight(0.05f), tint = colorResource(id=R.color.primaryColor)
//                )
//                Text("Profile Picture", modifier = Modifier.weight(0.2f))
//                Icon(
//                    painter = painterResource(id = R.drawable.arrow_right),
//                    contentDescription = null,
//                    modifier = Modifier.weight(0.05f),
//                    tint = colorResource(id=R.color.textColor)
//                )
//            }
//            Spacer(modifier = Modifier.height(15.dp))
//
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(70.dp)
//
//                    .background(Color(0x8DB3B0B0), shape = RoundedCornerShape(10.dp))
//                    .clip(RoundedCornerShape(10.dp))
//                    .clickable {
//
//                    }
//                    .padding(5.dp),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Icon(
//                    painter = painterResource(id = R.drawable.settings),
//                    contentDescription = null,
//                    modifier = Modifier.weight(0.05f), tint = colorResource(id=R.color.primaryColor)
//                )
//                Text("Settings", modifier = Modifier.weight(0.2f))
//                Icon(
//                    painter = painterResource(id = R.drawable.arrow_right),
//                    contentDescription = null,
//                    modifier = Modifier.weight(0.05f),
//                    tint = colorResource(id=R.color.textColor)
//                )
//            }
//
//            Spacer(modifier = Modifier.height(15.dp))
//
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(70.dp)
//
//                    .background(Color(0x8DB3B0B0), shape = RoundedCornerShape(10.dp))
//                    .clip(RoundedCornerShape(10.dp))
//                    .clickable {
//
//                    }
//                    .padding(5.dp),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Icon(
//                    painter = painterResource(id = R.drawable.question_mark),
//                    contentDescription = null,
//                    modifier = Modifier.weight(0.05f), tint = colorResource(id=R.color.primaryColor)
//                )
//                Text("Help Center", modifier = Modifier.weight(0.2f))
//                Icon(
//                    painter = painterResource(id = R.drawable.arrow_right),
//                    contentDescription = null,
//                    modifier = Modifier.weight(0.05f),
//                    tint = colorResource(id=R.color.textColor)
//                )
//            }
//
//            Spacer(modifier = Modifier.height(15.dp))
//
//
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(70.dp)
//                    .background(Color(0x8DB3B0B0), shape = RoundedCornerShape(10.dp))
//                    .clip(RoundedCornerShape(10.dp))
//                    .clickable {
//
//                    }
//                    .padding(5.dp),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Icon(
//                    painter = painterResource(id = R.drawable.log_out),
//                    contentDescription = null,
//                    modifier = Modifier.weight(0.05f), tint = colorResource(id=R.color.primaryColor)
//                )
//                Text("Logout", modifier = Modifier.weight(0.2f))
//                Icon(
//                    painter = painterResource(id = R.drawable.arrow_right),
//                    contentDescription = null,
//                    modifier = Modifier.weight(0.05f),
//                    tint = colorResource(id=R.color.textColor)
//                )
//            }
//        }
//    }

    }

}

@Composable
private fun SettingCategory(
    icon: Painter,
    text: String,
    modifier: Modifier = Modifier,
    color: Color = if (HeliaTheme.theme.isDark) HeliaTheme.colors.white else HeliaTheme.colors.greyscale800,
    onClick: (() -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
            .padding(horizontal = 30.dp, vertical = 12.dp)
            .height(50.dp)
            ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(28.dp),
            painter = icon,
            contentDescription = null,
            tint = color
        )
        Spacer(modifier = Modifier.width(24.dp))
        Text(
            text = text,
            style = HeliaTheme.typography.bodyXLargeSemiBold,
            color = color
        )
    }
}

@Composable
private fun SettingThemeCategory(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
//        modifier = modifier.padding(end = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SettingCategory(
            icon = painterResource(id = R.drawable.ic_show_border),
            text = "Dark Theme"
        )
        Toggle(modifier = Modifier.padding(start = 150.dp),
            checked = checked,
            onCheckedChange = onCheckedChange)
    }
}

@Composable
private fun SettingLogoutCategory(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    SettingCategory(
        modifier = modifier,
        icon = painterResource(id = R.drawable.ic_logout_border),
        text = "Log Out",
        onClick = onClick,
        color = HeliaTheme.colors.error
    )
}



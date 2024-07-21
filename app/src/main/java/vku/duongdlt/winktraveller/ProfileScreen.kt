package vku.duongdlt.winktraveller

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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import vku.duongdlt.winktraveller.component.ChildLayout
import vku.duongdlt.winktraveller.component.DefaultBackArrow
import vku.duongdlt.winktraveller.component.InformationCard
import vku.duongdlt.winktraveller.component.VerticalScrollLayout
import vku.duongdlt.winktraveller.component.homeHeader
import vku.duongdlt.winktraveller.component.profileHeader
import vku.duongdlt.winktraveller.util.BOTTOM_NAV_SPACE

import vku.duongdlt.winktraveller.navigation.Route
enum class ProfileScreenContents{
    HEADER_SECTION,
    INFORMATION_USER,
    CATEGORY_SECTION,
    DESTINATION_LARGE_SECTION,
    DESTINATION_VIEW_ALL,
    DESTINATION_SMALL_SECTION,
}
@Composable
fun ProfileScreen(routeState: MutableState<Route>){

    Surface(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 31.dp, bottom = BOTTOM_NAV_SPACE)) {
//        VerticalScrollLayout(
//            modifier = Modifier.fillMaxSize()
//                .background(color = MaterialTheme.colorScheme.background)
//                .padding(top=31.dp),
//            ChildLayout(
//                contentType = ProfileScreenContents.HEADER_SECTION.name,
//                content = {
//                    profileHeader()
//                }
//            ),
//            ChildLayout(
//                contentType = ProfileScreenContents.INFORMATION_USER.name,
//                content = {
//                    InformationCard()
//                }
//            )
//        )
//        Text(
//            modifier = Modifier.wrapContentSize(Alignment.Center),
//            text = "Profile",
//            color = colorResource(R.color.textColor),
//            style = MaterialTheme.typography.titleLarge
//        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { routeState.value = routeState.value.copy(
                        screen = routeState.value.prev ?: routeState.value.screen
                    )},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),

                ) {
                        Icon(
                            tint = colorResource(id=R.color.textColor),
                            painter = painterResource(id = R.drawable.back_icon),
                            contentDescription = null)
                }

                Box(modifier = Modifier.weight(0.7f)) {
                    Text(
                        text = "Profile",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

            }
            Spacer(modifier = Modifier.height(30.dp))
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                val (image, cameraIcon) = createRefs()
                Image(
                    painter = painterResource(id = R.drawable.profile_image),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .clip(CircleShape)
                        .constrainAs(image) {
                            linkTo(start = parent.start, end = parent.end)
                        }
                )
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.constrainAs(cameraIcon) {
                        bottom.linkTo(image.bottom)
                        end.linkTo(image.end)

                    }) {

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.camera_icon),
                            contentDescription = "Change Picture",
                            modifier = Modifier.background(Color.LightGray),
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(60.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)

                    .background(Color(0x8DB3B0B0), shape = RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {

                    }
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    painter = painterResource(id = R.drawable.user_icon),
                    contentDescription = null,
                    modifier = Modifier.weight(0.05f), tint = colorResource(id=R.color.primaryColor)
                )
                Text("Profile Picture", modifier = Modifier.weight(0.2f))
                Icon(
                    painter = painterResource(id = R.drawable.arrow_right),
                    contentDescription = null,
                    modifier = Modifier.weight(0.05f),
                    tint = colorResource(id=R.color.textColor)
                )
            }
            Spacer(modifier = Modifier.height(15.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)

                    .background(Color(0x8DB3B0B0), shape = RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {

                    }
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.settings),
                    contentDescription = null,
                    modifier = Modifier.weight(0.05f), tint = colorResource(id=R.color.primaryColor)
                )
                Text("Settings", modifier = Modifier.weight(0.2f))
                Icon(
                    painter = painterResource(id = R.drawable.arrow_right),
                    contentDescription = null,
                    modifier = Modifier.weight(0.05f),
                    tint = colorResource(id=R.color.textColor)
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)

                    .background(Color(0x8DB3B0B0), shape = RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {

                    }
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.question_mark),
                    contentDescription = null,
                    modifier = Modifier.weight(0.05f), tint = colorResource(id=R.color.primaryColor)
                )
                Text("Help Center", modifier = Modifier.weight(0.2f))
                Icon(
                    painter = painterResource(id = R.drawable.arrow_right),
                    contentDescription = null,
                    modifier = Modifier.weight(0.05f),
                    tint = colorResource(id=R.color.textColor)
                )
            }

            Spacer(modifier = Modifier.height(15.dp))


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .background(Color(0x8DB3B0B0), shape = RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {

                    }
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.log_out),
                    contentDescription = null,
                    modifier = Modifier.weight(0.05f), tint = colorResource(id=R.color.primaryColor)
                )
                Text("Logout", modifier = Modifier.weight(0.2f))
                Icon(
                    painter = painterResource(id = R.drawable.arrow_right),
                    contentDescription = null,
                    modifier = Modifier.weight(0.05f),
                    tint = colorResource(id=R.color.textColor)
                )
            }
        }
    }
}
//@Preview
//@Composable
//fun ProfileScreenPreview(){
//    ProfileScreen()
//}
package vku.duongdlt.winktraveller.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import vku.duongdlt.winktraveller.R
import vku.duongdlt.winktraveller.navigation.Screen

val customFontFamily = FontFamily(
    Font(R.font.custom, FontWeight.Normal) // Thay thế custom_font bằng tên file thực tế
)
@Composable
fun FirstScreen(navController: NavController) {
    // Đường dẫn tới resource của ảnh trong thư mục res/drawable
    val imageResId = R.drawable.image1

    // Lấy đối tượng Painter từ resource ảnh
    val painter: Painter = painterResource(id = imageResId)


    Surface(modifier = Modifier.fillMaxSize()  ){
        Row {
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (nameRow, list, card, topBar) = createRefs()
                Image(painter = painterResource(id = imageResId),
                    contentDescription = "abc",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(37.dp))
                        .constrainAs(topBar) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        })
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 25.dp, start = 16.dp, end = 16.dp)
                    .constrainAs(nameRow) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })
                {
                    Button(onClick = { /*TODO*/ },colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),modifier = Modifier.align(Alignment.TopEnd)) {
                        
                    Box(){
                    Text(text = "Skip",
                        color = Color.White,
                        modifier = Modifier.align(Alignment.TopEnd),
                        fontSize = 20.sp,

                        fontFamily = customFontFamily
                    )
                }}}
                TextItem(modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(list) {
                        top.linkTo(topBar.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)

                    })
                ButtonItem(onClickAction = { navController.navigate(Screen.LoginScreen.route)},text="Get Started",modifier = Modifier.constrainAs(card){
                    top.linkTo(list.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                })
            }
        }
    }
}

@Composable
fun TextItem(modifier: Modifier){
    Column(modifier = modifier) {
        Box(modifier = Modifier) {
            Text(text = "Life is short and the world is wide",
                color = Color.Black,
                modifier = Modifier.align(Alignment.Center),
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                fontFamily = customFontFamily,
                lineHeight = 50.sp
            )
        }
    }
}

@Composable
fun ButtonItem(onClickAction: () -> Unit,text :String,modifier: Modifier){
    Button(onClick = onClickAction,
        colors = ButtonDefaults.buttonColors(containerColor = Color(13,110,253))
        ,shape = RoundedCornerShape(16.dp),modifier = modifier
            .padding(16.dp)
            .width(335.dp)
            .height(56.dp)
        ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = text,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center,
            )
        }
    }
//    Column(modifier = modifier
//        .padding(16.dp)
//        .width(335.dp)
//        .height(56.dp)
//        .clip(RoundedCornerShape(16.dp))
//        .background(Color(13, 110, 253))
//        ) {
//        Box(modifier = Modifier.fillMaxSize()) {
//            Text(text = "Get Started",
//                color = Color.White,
//                modifier = Modifier.align(Alignment.Center),
//                textAlign = TextAlign.Center,
//            )
//        }
//    }
}

//@Preview(s    howBackground = true)
//@Composable
//fun FirstScreenPreview() {
//    WinKTravellerTheme {
//        FirstScreen()
//    }
//}
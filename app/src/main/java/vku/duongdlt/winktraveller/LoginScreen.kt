package vku.duongdlt.winktraveller

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import vku.duongdlt.winktraveller.ViewModel.UserViewModel
import vku.duongdlt.winktraveller.common.isEmptyString
import vku.duongdlt.winktraveller.model.User
import vku.duongdlt.winktraveller.navigation.Route
import vku.duongdlt.winktraveller.navigation.Screen
import vku.duongdlt.winktraveller.ui.theme.WinKTravellerTheme

@Composable
fun LoginScreen(routeState: MutableState<Route>,userViewModel: UserViewModel) {
    var userName by remember { mutableStateOf("") }
    var passWord by remember { mutableStateOf("") }
    var num by remember { mutableStateOf("") }
    var rememberMeState by remember { mutableStateOf(false) }
    var check by remember { mutableStateOf(false) }
    var user_role_id by remember { mutableStateOf(0) }
    var errorText by remember { mutableStateOf("") }
    var showDiaLogForgotPass by remember { mutableStateOf(false) }

    var listUser by remember {
        mutableStateOf(emptyList<User>())
    }

    userViewModel.getAllUsers {
        listUser = it
    }

    if (userName != "" && passWord != "") {
        listUser.forEach { user ->
            if (user.user_username == userName && user.user_password == passWord) {
                check = true
                user_role_id= user.role_id
            }
        }
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier

                .padding(top = 25.dp, start = 16.dp, end = 16.dp)
        )
        {
            Button(
                onClick = { routeState.value = routeState.value.copy(
                    screen = routeState.value.prev ?: routeState.value.screen
                )},
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                modifier = Modifier.align(Alignment.TopStart)
            ) {

                Box() {
                    Text(
                        text = "<",
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.TopEnd),
                        fontSize = 20.sp,

                        fontFamily = customFontFamily
                    )
                }
            }
            Box(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {


                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Login Now", fontSize =
                        30.sp, fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Please sign in to continue our app")
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = userName,
                        onValueChange = {
                            userName = it
                        },
                        label = { Text(text = "UserName") },
                        keyboardOptions = KeyboardOptions(
                            autoCorrect = true,
                            keyboardType = KeyboardType.Text,
                        ),
                        maxLines = 1,
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp), modifier = Modifier
                            .padding(16.dp)
                            .width(335.dp)
                            .height(60.dp)
                    )

                    OutlinedTextField(
                        value = passWord,
                        onValueChange = {
                            passWord = it
                        },
                        label = { Text(text = "Password") },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            autoCorrect = true,
                            keyboardType = KeyboardType.Password,
                        ),
                        maxLines = 1,
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp), modifier = Modifier
                            .padding(16.dp)
                            .width(335.dp)
                            .height(60.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        text = errorText,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        color = Color.Red
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ButtonItem(onClickAction = {
                        if(isEmptyString(userName)) {
                            errorText = "invalid Username"
                        }else if(isEmptyString(passWord)) {
                            errorText = "invalid Password"
                        } else {
                            if (check) {
                                if (user_role_id == 1) {
//                                    navController.navigate(Screens.AdminScreen.route)
                                }else {
                                    routeState.value = Route(
                                        screen = Screen.HomeScreen
                                    )
//                                    navController.navigate("${Screens.MainScreen.route}/$userName")
                                }
                            }else {
                                errorText = "Error username or password"
                            }
                        }
                        },
                        text = "Login",
                        modifier = Modifier
                    )
                    Row {

                        Column(modifier = Modifier.padding(top = 11.dp)) {
                            Text(text = "Don’t have an account?")
                        }
                        Column() {
                            Button(
                                onClick = { routeState.value = Route(
                                    screen = Screen.SignUpScreen,
                                    prev = Screen.LoginScreen
                                ) },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                            ) {

                                Box() {
                                    Text(text = "SignUp", color = Color(13, 110, 253))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun LoginScreenPreview() {
//    WinKTravellerTheme {
//        LoginScreen()
//    }
//}
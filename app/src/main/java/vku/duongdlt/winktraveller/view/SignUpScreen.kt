package vku.duongdlt.winktraveller.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import vku.duongdlt.winktraveller.ViewModel.UserViewModel
import vku.duongdlt.winktraveller.common.isEmptyString
import vku.duongdlt.winktraveller.common.isValidEmail
import vku.duongdlt.winktraveller.model.User
import vku.duongdlt.winktraveller.navigation.Route
import vku.duongdlt.winktraveller.navigation.Screen

@Composable
fun SignUpScreen(routeState: MutableState<Route>, userViewModel: UserViewModel) {
    var userName by remember { mutableStateOf("") }
    var passWord by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }
    var checkMail by remember { mutableStateOf(true) }
    var checkUserName by remember { mutableStateOf(true) }

    val coroutineScope = rememberCoroutineScope()

    // Fetch user list and perform checks
    var listUser by remember { mutableStateOf(emptyList<User>()) }
    userViewModel.getAllUsers {
        listUser = it
    }

    listUser.forEach { user ->
        if (email == user.user_email) {
            checkMail = false
        } else {
            checkMail = true
        }

        if (user.user_username == userName) {
            checkUserName = false
        } else {
            checkUserName = true
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.padding(top = 25.dp, start = 16.dp, end = 16.dp)
        ) {
            Button(
                onClick = { routeState.value = routeState.value.copy(screen = routeState.value.prev ?: routeState.value.screen) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Box {
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
                        text = "Sign up Now",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Please fill the details and create an account")
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = userName,
                        onValueChange = { userName = it },
                        keyboardOptions = KeyboardOptions(autoCorrect = true, keyboardType = KeyboardType.Text),
                        maxLines = 1,
                        singleLine = true,
                        label = { Text(text = "Name") },
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.padding(16.dp).width(335.dp).height(56.dp)
                    )

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text(text = "Email") },
                        keyboardOptions = KeyboardOptions(autoCorrect = true, keyboardType = KeyboardType.Email),
                        maxLines = 1,
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.padding(16.dp).width(335.dp).height(60.dp)
                    )

                    OutlinedTextField(
                        value = passWord,
                        onValueChange = { passWord = it },
                        label = { Text(text = "Password") },
                        keyboardOptions = KeyboardOptions(autoCorrect = true, keyboardType = KeyboardType.Password),
                        visualTransformation = PasswordVisualTransformation(),
                        maxLines = 1,
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.padding(16.dp).width(335.dp).height(60.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = errorText,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Red
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ButtonItem(onClickAction = {
                        if (isEmptyString(userName)) {
                            errorText = "Invalid username"
                        } else if (isEmptyString(passWord)) {
                            errorText = "Invalid password"
                        } else if (!isValidEmail(email)) {
                            errorText = "Invalid email"
                        } else {
                            if (!checkUserName) {
                                errorText = "Username already exists"
                            } else if (!checkMail) {
                                errorText = "Email already exists"
                            } else {
                                coroutineScope.launch {
                                    val success = userViewModel.registerUser(userName,email, passWord)
                                    if (success) {
                                        // User registration successful, navigate to login screen
                                        routeState.value = Route(screen = Screen.LoginScreen, prev = Screen.SignUpScreen)
                                    } else {
                                        // Registration failed, show error
                                        errorText = "Registration failed"
                                    }
                                }
                            }
                        }
                    }, text = "Sign Up", modifier = Modifier)
                    Row{
                        Column(modifier = Modifier.padding(top = 11.dp)) {
                            Text(text = "Already have an account?")
                        }
                        Column {
                            Button(
                                onClick = { routeState.value = Route(screen = Screen.LoginScreen, prev = Screen.SignUpScreen) },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                            ) {
                                Box {
                                    Text(text = "Login", color = Color(13, 110, 253))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
//
//@Composable
//fun SignUpScreen(routeState: MutableState<Route>, userViewModel: UserViewModel) {
//    var userName by remember { mutableStateOf("") }
//    var passWord by remember { mutableStateOf("") }
//    var email by remember { mutableStateOf("") }
//    var i by remember { mutableStateOf(0) }
//
//    var listUser by remember {
//        mutableStateOf(emptyList<User>())
//    }
//
//    var errorText by remember { mutableStateOf("") }
//    var checkMail by remember { mutableStateOf(true) }
//    var checkUserName by remember { mutableStateOf(true) }
//
//    userViewModel.getAllUsers {
//        listUser = it
//    }
//
//    listUser.forEach { user ->
//        if(user.id > i) {
//            i = user.id
//        }
//
//        if(email == user.user_email) {
//            checkMail = false
//        }else {
//            checkMail = true
//        }
//
//        if(user.user_username == userName) {
//            checkUserName = false
//        }else {
//            checkUserName = true
//        }
//    }
//
//    Surface(modifier = Modifier.fillMaxSize()) {
//        Box(
//            modifier = Modifier
//
//                .padding(top = 25.dp, start = 16.dp, end = 16.dp)
//        )
//        {
//            Button(
//                onClick = { routeState.value = routeState.value.copy(
//                    screen = routeState.value.prev ?: routeState.value.screen
//                ) },
//                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
//                modifier = Modifier.align(Alignment.TopStart)
//            ) {
//
//                Box() {
//                    Text(
//                        text = "<",
//                        color = Color.Black,
//                        modifier = Modifier.align(Alignment.TopEnd),
//                        fontSize = 20.sp,
//
//                        fontFamily = customFontFamily
//                    )
//                }
//            }
//            Box(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
//
//
//                Column(
//                    modifier = Modifier.fillMaxSize(),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Text(
//                        text = "Sign up Now", fontSize =
//                        30.sp, fontWeight = FontWeight.Bold
//                    )
//                    Spacer(modifier = Modifier.height(16.dp))
//                    Text(text = "Please fill the details and create account")
//                    Spacer(modifier = Modifier.height(16.dp))
//                    OutlinedTextField(
//                        value = userName,
//                        onValueChange = {
//                            userName = it
//                        },
//                        keyboardOptions = KeyboardOptions(
//                            autoCorrect = true,
//                            keyboardType = KeyboardType.Text,
//                        ),
//                        maxLines = 1,
//                        singleLine = true,
//                        label = { Text(text = "Name") },
//                        shape = RoundedCornerShape(16.dp), modifier = Modifier
//                            .padding(16.dp)
//                            .width(335.dp)
//                            .height(56.dp)
//                    )
//
//                    OutlinedTextField(
//                        value = email,
//                        onValueChange = {
//                            email = it
//                        },
//                        label = { Text(text = "Email") },
//                        keyboardOptions = KeyboardOptions(
//                            autoCorrect = true,
//                            keyboardType = KeyboardType.Email,
//                        ),
//                        maxLines = 1,
//                        singleLine = true,
//                        shape = RoundedCornerShape(16.dp), modifier = Modifier
//                            .padding(16.dp)
//                            .width(335.dp)
//                            .height(60.dp)
//                    )
//
//                    OutlinedTextField(
//                        value = passWord,
//                        onValueChange = {
//                            passWord = it
//                        },
//                        label = { Text(text = "Password") },
//                        keyboardOptions = KeyboardOptions(
//                            autoCorrect = true,
//                            keyboardType = KeyboardType.Password,
//                        ),
//                        visualTransformation = PasswordVisualTransformation(),
//                        maxLines = 1,
//                        singleLine = true,
//                        shape = RoundedCornerShape(16.dp), modifier = Modifier
//                            .padding(16.dp)
//                            .width(335.dp)
//                            .height(60.dp)
//                    )
//                    Spacer(modifier = Modifier.height(16.dp))
//                    Text(
//                        text = errorText,
//                        fontSize = 12.sp,
//                        fontWeight = FontWeight.SemiBold,
//                        color = Color.Red
//                    )
//                    Spacer(modifier = Modifier.height(16.dp))
//                    ButtonItem(onClickAction = {
//                        if (isEmptyString(userName)) {
//                            errorText = "invalid username"
//                        } else if (isEmptyString(passWord)) {
//                            errorText = "invalid password"
//                        } else if (!isValidEmail(email)) {
//                            errorText = "error email"
//                        } else {
//                            if(checkUserName == false) {
//                                errorText = "username already exists"
//                            }else if(checkMail == false){
//                                errorText = "email already exists"
//                            }else {
//                                val user = User(++i, user_username = userName, user_password = passWord, user_email = email)
//
//                                userViewModel.addUser(user)
//                                routeState.value = Route(
//                                    screen = Screen.LoginScreen,
//                                    prev = Screen.SignUpScreen)
//                            }
//                        }
//                    },
//                        text = "Sign Up",
//                        modifier = Modifier)
//                    Row {
//
//                        Column(modifier = Modifier.padding(top = 11.dp)) {
//                            Text(text = "Already have an account")
//                        }
//                        Column() {
//                            Button(
//                                onClick = { routeState.value = Route(
//                                    screen = Screen.LoginScreen,
//                                    prev = Screen.SignUpScreen
//                                ) },
//                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
//                            ) {
//
//                                Box() {
//                                    Text(text = "Login", color = Color(13, 110, 253))
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun SignUpScreenPreview() {
//    WinKTravellerTheme {
//        SignUpScreen()
//    }
//}
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
import vku.duongdlt.winktraveller.navigation.Route
import vku.duongdlt.winktraveller.navigation.Screen

@Composable
fun LoginScreen(routeState: MutableState<Route>, userViewModel: UserViewModel) {
    var userName by remember { mutableStateOf("") }
    var passWord by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.padding(top = 25.dp, start = 16.dp, end = 16.dp)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Login Now", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Please sign in to continue our app")
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = userName,
                    onValueChange = { userName = it },
                    label = { Text(text = "Email") },
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = true,
                        keyboardType = KeyboardType.Email
                    ),
                    maxLines = 1,
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.padding(16.dp).width(335.dp).height(60.dp)
                )
                OutlinedTextField(
                    value = passWord,
                    onValueChange = { passWord = it },
                    label = { Text(text = "Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = true,
                        keyboardType = KeyboardType.Password
                    ),
                    maxLines = 1,
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.padding(16.dp).width(335.dp).height(60.dp)
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
                    coroutineScope.launch {
                        val success = userViewModel.loginUser(userName, passWord)
                        if (success) {
                            routeState.value = Route(screen = Screen.HomeScreen)
                        } else {
                            errorText = "Invalid username or password"
                        }
                    }
                }, text = "Login", modifier = Modifier)
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Don’t have an account?")
                    Button(onClick = {
                        routeState.value = Route(
                            screen = Screen.SignUpScreen,
                            prev = Screen.LoginScreen
                        )

                    },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                    ) {
                        Text(text = "SignUp", color = Color(13, 110, 253))
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
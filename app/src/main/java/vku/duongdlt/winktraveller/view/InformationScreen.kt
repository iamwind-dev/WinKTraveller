package vku.duongdlt.winktraveller.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import vku.duongdlt.winktraveller.R
import vku.duongdlt.winktraveller.ViewModel.UserViewModel
import vku.duongdlt.winktraveller.model.User
import vku.duongdlt.winktraveller.navigation.Route
import vku.duongdlt.winktraveller.navigation.Screen

@Composable
fun InformationScreen(routeState: MutableState<Route>, userViewModel: UserViewModel = viewModel()) {
    var user by remember { mutableStateOf<User?>(null) }
    LaunchedEffect(Unit) {
        userViewModel.getCurrentUser {
            user = it
        }
    }


    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    modifier = Modifier
                        .size(25.dp)
                        .padding(start = 4.dp)
                        .clickable {
                            routeState.value = Route(screen = Screen.ProfileScreen)
                        },
                    painter = painterResource(id = R.drawable.back_icon),
                    contentDescription = null
                )
            }

            Row {
                user?.let { FormPage2(routeState, it, userViewModel) }

            }
        }

    }
}

@Composable
fun FormPage2(routeState: MutableState<Route>, user: User, userViewModel: UserViewModel) {
    var fullName by remember { mutableStateOf(user.user_name) }
    var email by remember { mutableStateOf(user.user_email) }
    var phone by remember { mutableStateOf(user.user_phone) }
    val context = LocalContext.current

    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        Row(modifier = Modifier
            .weight(1f)
            .verticalScroll(rememberScrollState())) {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(text = "Personal Details", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Full Name", style = MaterialTheme.typography.titleMedium)
                OutlinedTextField(
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth(),
                    value = fullName,
                    onValueChange = { fullName = it }
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Email", style = MaterialTheme.typography.titleMedium)
                OutlinedTextField(
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth(),
                    value = email,
                    onValueChange = { email = it }
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Phone", style = MaterialTheme.typography.titleMedium)
                OutlinedTextField(
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth(),
                    value = phone,
                    onValueChange = { phone = it }
                )
            }
        }
        ButtonRow {
            val fieldsToUpdate = mapOf(
                "user_name" to fullName,
                "user_email" to email,
                "user_phone" to phone
            )
            userViewModel.updateUserField(user.id, fieldsToUpdate) { success ->
                if (success) {
                    Toast.makeText(
                        context,
                        context.getString(R.string.complete),
                        Toast.LENGTH_SHORT
                    ).show()
                    println("User data updated successfully")
                } else {
                    println("Failed to update user data")
                }
            }
        }
    }
}


//@Preview
//@Composable
//fun FormPage2Preview(){
//    Surface(modifier = Modifier.fillMaxSize()) {
//        FormPage2()
//    }
//
//}
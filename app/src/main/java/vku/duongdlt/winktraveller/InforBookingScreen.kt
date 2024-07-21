package vku.duongdlt.winktraveller

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vku.duongdlt.winktraveller.component.InputField
import vku.duongdlt.winktraveller.component.Navbar
import vku.duongdlt.winktraveller.component.PrimaryButton
import vku.duongdlt.winktraveller.component.TabLayout
import vku.duongdlt.winktraveller.component.TourSmallItem
import vku.duongdlt.winktraveller.formbuilder.BaseState
import vku.duongdlt.winktraveller.formbuilder.FormState
import vku.duongdlt.winktraveller.formbuilder.TextFieldState
import vku.duongdlt.winktraveller.model.Tour
import vku.duongdlt.winktraveller.navigation.Route
import vku.duongdlt.winktraveller.navigation.Screen
import vku.duongdlt.winktraveller.ui.theme.HeliaTheme
import vku.duongdlt.winktraveller.ui.theme.WinKTravellerTheme

@Composable
fun InforBookingScreen(routeState: MutableState<Route>,tour: Tour){
    val formState: FormState<BaseState<*>> = FormState(
        listOf(
            TextFieldState("username"),
            TextFieldState("email"),
            TextFieldState("number")
        )
    )
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
        ) {
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
                            routeState.value = Route(screen = Screen.BookingScreen(tour))
                        },
                    painter = painterResource(id = R.drawable.back_icon),
                    contentDescription = null
                )
                Column(
                ) {
                    Text(
                        text = "Step 2/2",
                        color = Color.Black,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
            Row {
                TourSmallItem(tour = tour)
            }
            Row(modifier = Modifier.padding(16.dp)) {
                PersonalDetails(formState)
            }
            PrimaryButton(
                title = "Continue",
                paddingValues = PaddingValues(
                    start = 25.dp,
                    top = 36.dp,
                    end = 25.dp,
                    bottom = 36.dp,


                    ),
                onClick = {routeState.value = Route(
                    screen = Screen.InforBookingScreen(tour),
                    prev = Screen.BookingScreen(tour)
                )}
            )
        }
    }
}

@Composable
fun PersonalDetails(formState: FormState<BaseState<*>>) {
    val usernameState: TextFieldState = formState.getState("username")
    val emailState: TextFieldState = formState.getState("email")
    val numberState: TextFieldState = formState.getState("number")

    Column(
        modifier = Modifier.padding(top=16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = CenterHorizontally
    ) {
        Text(
            modifier = Modifier.width(400.dp),
            text = "Personal Details",
            style = HeliaTheme.typography.heading6
        )



        Text(text = "Name",modifier= Modifier
            .align(Start)
            .padding(start = 16.dp))
        InputField(value = "", onValueChange = {}, placeholderText = "",modifier = Modifier.width(400.dp))


        Text(text = "Email",modifier= Modifier
            .align(Start)
            .padding(start = 16.dp))
        InputField(value = "", onValueChange = {}, placeholderText = "",modifier = Modifier.width(400.dp))

        Text(text = "Phone",modifier= Modifier
            .align(Start)
            .padding(start = 16.dp))

        InputField(value = "", onValueChange = {}, placeholderText = "", modifier = Modifier.width(400.dp))


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInput(label: String, state: TextFieldState) {

    Column {
        OutlinedTextField(
            value = state.value,
            isError = state.hasError,
            label = { Text(text = label) },
            modifier = Modifier
                .width(360.dp)
                .height(60.dp),
            shape = RoundedCornerShape(16.dp),
            onValueChange = { state.change(it) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                errorBorderColor = HeliaTheme.colors.error,
                focusedBorderColor = HeliaTheme.colors.blue,
                unfocusedBorderColor = HeliaTheme.colors.blue
            )
        )

        if (state.hasError) {
            Text(
                text = state.errorMessage,
                modifier = Modifier.padding(start = 12.dp, top = 4.dp),
                style = HeliaTheme.typography.heading2.copy(
                    color = HeliaTheme.colors.error
                )
            )
        }
    }
}


@Composable
@Preview
fun PersonalDetailsPreview() {
    val formState: FormState<BaseState<*>> = FormState(
        listOf(
            TextFieldState("username"),
            TextFieldState("email"),
            TextFieldState("number")
        )
    )

    WinKTravellerTheme {


    }
}

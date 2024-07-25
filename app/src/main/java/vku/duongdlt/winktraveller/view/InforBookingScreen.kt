package vku.duongdlt.winktraveller.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import vku.duongdlt.winktraveller.R
import vku.duongdlt.winktraveller.ViewModel.BookingViewModel
import vku.duongdlt.winktraveller.ViewModel.UserViewModel
import vku.duongdlt.winktraveller.component.InputField
import vku.duongdlt.winktraveller.component.PrimaryButton
import vku.duongdlt.winktraveller.component.TourSmallItem
import vku.duongdlt.winktraveller.formbuilder.BaseState
import vku.duongdlt.winktraveller.formbuilder.FormState
import vku.duongdlt.winktraveller.formbuilder.TextFieldState
import vku.duongdlt.winktraveller.model.Booking
import vku.duongdlt.winktraveller.model.Tour
import vku.duongdlt.winktraveller.model.User
import vku.duongdlt.winktraveller.navigation.Route
import vku.duongdlt.winktraveller.navigation.Screen
import vku.duongdlt.winktraveller.ui.theme.HeliaTheme
import vku.duongdlt.winktraveller.ui.theme.WinKTravellerTheme
import vku.duongdlt.winktraveller.util.getCurrentDate
import java.time.LocalDate

@Composable
fun InforBookingScreen(routeState: MutableState<Route>, tour: Tour,booking: Booking,user: User,userViewModel: UserViewModel) {
    // State to manage form data
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

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
                Column {
                    Text(
                        text = "Step 2/2",
                        color = Color.Black,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }

            Row {
                Column(modifier = Modifier.padding(16.dp)) {
                    TourSmallItem(tour = tour)
                }
            }

            Row {
                Column(modifier = Modifier.padding(16.dp)) {
                    // Pass state handlers to PersonalDetails
//                    PersonalDetails(
//                        name = name,
//                        onNameChange = { name = it },
//                        email = email,
//                        onEmailChange = { email = it },
//                        phone = phone,
//                        onPhoneChange = { phone = it }
//                    )
                    FormPage(routeState,tour,booking,user,userViewModel)
                }
            }


        }
    }
}



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FormPage(routeState: MutableState<Route>,tour: Tour,booking: Booking,user: User,userViewModel: UserViewModel) {
    val bookingViewModel = BookingViewModel()
    var user by remember { mutableStateOf<User?>(null) }
    LaunchedEffect(Unit) {
        userViewModel.getCurrentUser {
            user = it
        }
    }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }


    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        Row(modifier = Modifier
            .weight(1f)
            .verticalScroll(rememberScrollState())) {
            Column(modifier = Modifier.fillMaxSize() ) {
                Text(text = "Personal Details", style = MaterialTheme.typography.titleLarge)

                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Full Name", style = MaterialTheme.typography.titleMedium)
                OutlinedTextField(
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth(),
                    value = name, onValueChange = {
                        name =it
                    })
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Email", style = MaterialTheme.typography.titleMedium)
                user?.let {
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .fillMaxWidth(),
                        value = it.user_email, onValueChange = {
                            email = it
                        })
                }
                Text(text = "We will send confirmation information to duongdinh@gmail.com", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Phone", style = MaterialTheme.typography.titleMedium)
                OutlinedTextField(
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth(),
                    value = phone, onValueChange = {
                        phone = it
                    })
                Text(text = "Address", style = MaterialTheme.typography.titleMedium)
                OutlinedTextField(
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth(),
                    value = address, onValueChange = {
                        address=it
                    })
                    }
                }
        ButtonRow {
            val totalamount = tour.tour_price * booking.booking_capacity
            var updatedBooking :Booking
            if(email.isEmpty()) {
                user?.user_email?.let {
                updatedBooking =
                    booking.copy(
                        booking_custumer_name = name,
                        booking_custumer_email = it,
                        booking_user_id = user?.id,
                        booking_total = totalamount,
                        booking_date_start = tour.tour_start_date,
                        booking_date_end = tour.tour_end_date,
                        booking_customer_address = address,
                        booking_tour_id = tour.tour_id,
                        booking_custumer_phone = phone,
                        booking_date = getCurrentDate()
                    )
                    bookingViewModel.addBooking(updatedBooking)
                    user?.let {
                        routeState.value = Route(
                            screen =
                            Screen.ConfirmationScreen(
                                updatedBooking, tour
                            ), prev = Screen.InforBookingScreen(tour,booking, user!!)
                        )
                    }
                }

            } else {
                updatedBooking =
                    booking.copy(
                        booking_custumer_name = name,
                        booking_custumer_email = email,
                        booking_user_id = user?.id,
                        booking_total = totalamount,
                        booking_date_start = tour.tour_start_date,
                        booking_date_end = tour.tour_end_date,
                        booking_customer_address = address,
                        booking_tour_id = tour.tour_id,
                        booking_custumer_phone = phone,
                        booking_date = getCurrentDate()
                    )
                bookingViewModel.addBooking(updatedBooking)
                user?.let {
                    routeState.value = Route(
                        screen =
                        Screen.ConfirmationScreen(
                            updatedBooking, tour
                        ), prev = Screen.InforBookingScreen(tour,booking, user!!)
                    )
                }
            }


        }
            }
        }

@Composable
fun ButtonRow(nextClicked: () -> Unit) {
    Row {

        Button(
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(3.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.TextColor)),
            onClick = {
                nextClicked()
            }
        ) {
            Text("Confirm")
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

package vku.duongdlt.winktraveller.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import vku.duongdlt.winktraveller.R
import vku.duongdlt.winktraveller.ViewModel.BookingViewModel
import vku.duongdlt.winktraveller.ViewModel.TourViewModel
import vku.duongdlt.winktraveller.ViewModel.UserViewModel
import vku.duongdlt.winktraveller.component.ListHotelCardDetails
import vku.duongdlt.winktraveller.component.ListHotelCardPriceAndBookmark
import vku.duongdlt.winktraveller.component.TourSmallItem
import vku.duongdlt.winktraveller.model.Booking
import vku.duongdlt.winktraveller.model.Tour
import vku.duongdlt.winktraveller.model.User
import vku.duongdlt.winktraveller.util.BOTTOM_NAV_SPACE

import vku.duongdlt.winktraveller.navigation.Route
import vku.duongdlt.winktraveller.navigation.Screen
import vku.duongdlt.winktraveller.ui.theme.HeliaTheme
import vku.duongdlt.winktraveller.util.ImageItem
import vku.duongdlt.winktraveller.util.formatCurrency

@Composable
fun CartScreen(
    routeState: MutableState<Route>,
    userViewModel: UserViewModel,
    tourViewModel: TourViewModel,
    bookingViewModel: BookingViewModel
) {
    var user by remember { mutableStateOf<User?>(null) }
    var bookings by remember { mutableStateOf<List<Booking>>(emptyList()) }
    var tours by remember { mutableStateOf<Map<String, Tour>>(emptyMap()) }

    LaunchedEffect(Unit) {
        userViewModel.getCurrentUser { fetchedUser ->
            user = fetchedUser
            // Fetch bookings only after user is fetched
            if (user != null) {
                bookingViewModel.getBookingsByUserId(user!!.id) { fetchedBookings ->
                    bookings = fetchedBookings
                    // Fetch tour for each booking
                    fetchedBookings.forEach { booking ->
                        tourViewModel.getTourByBookingtourId(booking.booking_tour_id) { fetchedTour ->
                            fetchedTour?.let { tour ->
                                tours = tours.toMutableMap().apply {
                                    put(booking.booking_tour_id, tour)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(top = 31.dp)
    ) {
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 36.dp),
                text = "Favorite Destinations",
                color = colorResource(R.color.textColor),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
        }
        item {
            Spacer(
                modifier = Modifier.padding(top = 16.dp),
            )
        }
        item {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {


            if (bookings.isNotEmpty()) {
                bookings.forEach { booking ->
                    val tour = tours[booking.booking_tour_id]
                    if (tour != null) {
                        BookingSmallItem(
                            modifier = Modifier.fillMaxWidth(),
                            tour = tour,
                            booking = booking,
                            routeState = routeState
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            } else {
                Text(text = "...", modifier = Modifier.padding(vertical = 8.dp))
            }


        }
    }}
}


@Composable
fun BookingSmallItem(
    tour: Tour,
    booking: Booking,
    modifier: Modifier = Modifier,
    routeState: MutableState<Route>
) {
    Surface(
        modifier = modifier.height(IntrinsicSize.Min),
        shape = HeliaTheme.shapes.medium,
        shadowElevation = 2.dp,
        color = if (HeliaTheme.theme.isDark) HeliaTheme.colors.dark2 else HeliaTheme.colors.white,

        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            ImageItem(
                data = tour.url,
                modifier = Modifier
                    .width(95.dp)
                    .height(84.dp),
            )
            Column(modifier = modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
                Text(
                    text = tour.tour_name,
                    style = HeliaTheme.typography.heading5,
                    color = if (HeliaTheme.theme.isDark) HeliaTheme.colors.white else HeliaTheme.colors.greyscale900,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = tour.tour_location_name,
                    style = HeliaTheme.typography.bodyMediumRegular,
                    color = if (HeliaTheme.theme.isDark) HeliaTheme.colors.greyscale300 else HeliaTheme.colors.greyscale700,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Column(
                    modifier = modifier.padding(vertical = 6.dp),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = formatCurrency(booking.booking_total),
                            style = HeliaTheme.typography.heading4,
                            color = HeliaTheme.colors.bluex
                        )

                    }
                }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(3.dp),
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.TextColor)),
                    onClick = {
                       routeState.value = Route(screen = Screen.ConfirmationScreen(booking,tour))
                    }
                ) {
                    Text(text = "Xem chi tiết")
                }
            }
        }
    }
}


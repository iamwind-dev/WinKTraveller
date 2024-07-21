package vku.duongdlt.winktraveller.component

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vku.duongdlt.winktraveller.navigation.Route
import vku.duongdlt.winktraveller.R
import vku.duongdlt.winktraveller.data.FakeFavorites
import vku.duongdlt.winktraveller.model.Destination
import vku.duongdlt.winktraveller.model.Tour
import vku.duongdlt.winktraveller.navigation.Screen
import vku.duongdlt.winktraveller.ui.theme.WinKTravellerTheme

@Composable
fun homeHeader(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Image(modifier = Modifier
            .size(25.dp)
            .padding(start = 4.dp),
            painter = painterResource(id = R.drawable.search_icon),
            contentDescription = null
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Current Location",
                color = Color.Black,
                style = MaterialTheme.typography.titleSmall
            )
            Row(
                modifier = Modifier.padding(top = 1.dp),
                verticalAlignment = Alignment.CenterVertically,
            ){
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(R.drawable.ci_location),
                    contentDescription = null,
                  tint = colorResource(id = R.color.primaryColor)
                )
                val items = listOf("Option 1", "Option 2", "Option 3")
                MyDropdownMenu(list = items) {

                }


            }
        }
        Image(
            modifier = Modifier.size(36.dp),
            painter = painterResource(id = R.drawable.profile_icon),
            contentDescription = null
        )
    }
}

@Composable
fun BookingHeader(routeState: MutableState<Route>,tour: Tour) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Image(modifier = Modifier
            .size(25.dp)
            .padding(start = 4.dp)
            .clickable {
                routeState.value = Route(screen = Screen.DetailScreen(tour))
            },
            painter = painterResource(id = R.drawable.back_icon),
            contentDescription = null
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Step 1/2",
                color = Color.Black,
                style = MaterialTheme.typography.titleSmall
            )
           }
    }
}

@Composable
fun tourDetailHeader(routeState: MutableState<Route>, tour: Tour) {
    val isFav = remember { mutableStateOf(FakeFavorites.checkFavorite(tour)) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp, start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier
                .size(36.dp)
                .clickable {
                    routeState.value = Route(screen = Screen.HomeScreen)
                },
            painter = painterResource(R.drawable.back),
            contentDescription = null
        )

        Box(
            modifier = Modifier
                .size(36.dp)
                .background(
                    color = colorResource(R.color.white),
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable {
                    if (isFav.value) {
                        FakeFavorites.removeFavorite(tour)
                        isFav.value = false
                    } else {
                        FakeFavorites.addFavorite(tour)
                        isFav.value = true
                    }
                }
        ) {
            val tintColor = if (isFav.value) R.color.red else R.color.textColor
            Icon(
                modifier = Modifier.padding(6.dp),
                painter = painterResource(R.drawable.menu_fav),
                tint = colorResource(tintColor),
                contentDescription = null
            )
        }
    }
}

//@Composable
//fun destinationDetailHeader(routeState: MutableState<Route>, destination: Destination) {
//    val isFav = remember { mutableStateOf(FakeFavorites.checkFavorite(tour)) }
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(top = 40.dp, start = 16.dp, end = 16.dp),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceBetween
//    ) {
//        Image(
//            modifier = Modifier
//                .size(36.dp)
//                .clickable {
//                    routeState.value = routeState.value.copy(
//                        screen = routeState.value.prev ?: routeState.value.screen
//                    )
//                },
//            painter = painterResource(R.drawable.back),
//            contentDescription = null
//        )
//
//        Box(
//            modifier = Modifier
//                .size(36.dp)
//                .background(
//                    color = colorResource(R.color.white),
//                    shape = RoundedCornerShape(8.dp)
//                )
//                .clickable {
//                    if (isFav.value) {
//                        FakeFavorites.removeFavorite(destination)
//                        isFav.value = false
//                    } else {
//                        FakeFavorites.addFavorite(destination)
//                        isFav.value = true
//                    }
//                }
//        ) {
//            val tintColor = if (isFav.value) R.color.red else R.color.textColor
//            Icon(
//                modifier = Modifier.padding(6.dp),
//                painter = painterResource(R.drawable.menu_fav),
//                tint = colorResource(tintColor),
//                contentDescription = null
//            )
//        }
//    }
//}

@Composable
fun profileHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

    }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 36.dp, end = 16.dp),
                text = "Profile",
                color = colorResource(R.color.textColor),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
        }





@Preview(showBackground = true)
@Composable
fun FirstScreenPreview() {
    WinKTravellerTheme {
        homeHeader()
    }
}
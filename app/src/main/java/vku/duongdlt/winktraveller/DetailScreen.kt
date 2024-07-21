package vku.duongdlt.winktraveller

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import vku.duongdlt.winktraveller.ViewModel.TourViewModel
import vku.duongdlt.winktraveller.component.HtmlText


import vku.duongdlt.winktraveller.navigation.Route
import vku.duongdlt.winktraveller.component.PrimaryButton
import vku.duongdlt.winktraveller.component.TitleWithReview
import vku.duongdlt.winktraveller.component.tourDetailHeader
import vku.duongdlt.winktraveller.model.Destination
import vku.duongdlt.winktraveller.model.Location
import vku.duongdlt.winktraveller.model.Tour
import vku.duongdlt.winktraveller.navigation.Screen
import vku.duongdlt.winktraveller.ui.theme.HeliaTheme
import vku.duongdlt.winktraveller.util.ImageItem

@Composable
fun DetailScreen(routeState: MutableState<Route>, tour: Tour, tourviewModel: TourViewModel) {
    val rememberTour = remember { mutableStateOf(tour.url) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),

        bottomBar = {
            MyBottomAppBar(routeState = routeState, tour = tour)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            topSection(routeState, tour, rememberTour)
            contentSection(tour,tourviewModel) {
                rememberTour.value = it
            }
            // Uncomment the following PrimaryButton if needed
            // PrimaryButton(
            //     "Book Now",
            //     PaddingValues(start = 25.dp, top = 36.dp, end = 25.dp, bottom = 36.dp),
            //     onClick = {
            //         routeState.value = Route(
            //             screen = Screen.BookingScreen(tour),
            //             prev = Screen.DetailScreen(tour)
            //         )
            //     }
            // )
        }
    }
}


@Composable
fun topSection(routeState: MutableState<Route>, tour: Tour, thumbnail: MutableState<String>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
    ) {
       ImageItem(thumbnail.value)
        tourDetailHeader(routeState,tour)
    }
}

@Composable
fun contentSection(tour: Tour,tourviewModel: TourViewModel, onImageClicked: (String) -> Unit) {
    val images = remember { mutableStateOf<List<String>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            images.value = tourviewModel.getImagesFromFirebase("tour")
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .offset(y = -40.dp)
            .background(
                color = colorResource(id = R.color.white),
                shape = RoundedCornerShape(26.dp, 26.dp, 0.dp, 0.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp, top = 36.dp, end = 25.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = tour.tour_name,
                    color = colorResource(id=R.color.textColor),
                    style = HeliaTheme.typography.heading3
                )
                Row(
                    modifier = Modifier.padding(top = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(id=R.drawable.ci_location),
                        contentDescription = null,
                        tint = colorResource(id=R.color.TextColor)
                    )
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = tour.tour_location_name,
                        color = colorResource(id=R.color.TextColor),
                        style = HeliaTheme.typography.bodyLargeBold
                    )
                }
            }
            Column(
                modifier = Modifier.padding(top = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = tour.tour_price.toString()+" $",
                    color = colorResource(id=R.color.textColor),
                    style = HeliaTheme.typography.bodyLargeBold,
                    fontSize = TextUnit(24f, TextUnitType.Sp)
                )
                Text(
                    text = "/${tour.tour_duration}",
                    color = colorResource(id=R.color.secondTextColor),
                    style = HeliaTheme.typography.bodyMediumBold
                )
            }
        }

        LazyRow(
            modifier = Modifier.padding(start = 25.dp, top = 18.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(items = images.value) { index, item ->
                ImageItem(
                    data = item,
                    modifier = Modifier.size(90.dp).clickable {
                        onImageClicked.invoke(item)
                    }
                )
            }
        }

        HtmlText(
            text = tour.tour_description,
            modifier = Modifier
                .padding(start = 25.dp, top = 18.dp, end = 25.dp),
            fontSize = 20.sp,
            style = HeliaTheme.typography.heading5
            )

//        Text(
//            modifier = Modifier.padding(start = 25.dp, top = 18.dp, end = 25.dp),
//            text = tour.tour_description,
//            color = colorResource(id=R.color.secondTextColor),
//            style = MaterialTheme.typography.bodyLarge,
//            overflow = TextOverflow.Ellipsis,
//            textAlign = TextAlign.Justify
//        )

        TitleWithReview("Preview", "4.8", R.drawable.star)


    }
}

@Composable
fun MyBottomAppBar(modifier: Modifier = Modifier, routeState: MutableState<Route>, tour: Tour) {
    BottomAppBar(
        modifier = Modifier.fillMaxWidth().height(130.dp),
        containerColor = colorResource(id = R.color.white),
        content = {
            PrimaryButton(
                "Book Now",
                PaddingValues(start = 25.dp, end = 25.dp),
                onClick = {
                    routeState.value = Route(
                        screen = Screen.BookingScreen(tour),
                        prev = Screen.DetailScreen(tour)
                    )
                }
            )
        }
    )
}
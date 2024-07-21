package vku.duongdlt.winktraveller

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import vku.duongdlt.winktraveller.ViewModel.LocationViewModel
import vku.duongdlt.winktraveller.ViewModel.TourViewModel

import vku.duongdlt.winktraveller.util.BOTTOM_NAV_SPACE
import vku.duongdlt.winktraveller.navigation.Route
import vku.duongdlt.winktraveller.component.ChildLayout
import vku.duongdlt.winktraveller.component.LoadItemAfterSafeCast
import vku.duongdlt.winktraveller.component.TitleWithViewAllItem
import vku.duongdlt.winktraveller.component.VerticalScrollLayout
import vku.duongdlt.winktraveller.component.destinationSmallItem
import vku.duongdlt.winktraveller.component.homeHeader
import vku.duongdlt.winktraveller.component.loadCategoryItems
import vku.duongdlt.winktraveller.component.loadDestinationLargeItems
import vku.duongdlt.winktraveller.component.loadLocationItems
import vku.duongdlt.winktraveller.component.loadTourLargeItems
import vku.duongdlt.winktraveller.component.tourSmallItem
import vku.duongdlt.winktraveller.data.FakeCategories
import vku.duongdlt.winktraveller.data.FakeDestinations
import vku.duongdlt.winktraveller.data.FakeDestinations.destinations
import vku.duongdlt.winktraveller.model.Category
import vku.duongdlt.winktraveller.model.Destination
import vku.duongdlt.winktraveller.model.Location
import vku.duongdlt.winktraveller.model.Tour
import vku.duongdlt.winktraveller.navigation.Screen

enum class HomeScreenContents{
    HEADER_SECTION,
    CATEGORY_VIEW_ALL,
    CATEGORY_SECTION,
    DESTINATION_LARGE_SECTION,
    DESTINATION_VIEW_ALL,
    DESTINATION_SMALL_SECTION,
}

@Composable
fun HomeScreen(
    routeState: MutableState<Route>,
    locationViewModel: LocationViewModel,
    tourViewModel: TourViewModel
){
    var listLocation by remember { mutableStateOf(emptyList<Location>()) }
    val list = arrayListOf<Location>()

    locationViewModel.getAllLocation {
        listLocation = it
    }

    listLocation.forEach { location ->
        list.add(Location(location.id, location.location_description, location.location_image_url, location.location_name))
    }

    var listTour by remember { mutableStateOf(emptyList<Tour>()) }
    val list2 = arrayListOf<Tour>()

    tourViewModel.getAllTour {
        listTour = it
    }

    listTour.forEach { tour ->
        list2.add(Tour(tour.id, tour.tour_create_date, tour.tour_description, tour.tour_duration, tour.tour_edit_date, tour.tour_end_date, tour.tour_highlights, tour.tour_image_url, tour.tour_includes, tour.tour_introduction, tour.tour_journey, tour.tour_location_id, tour.tour_location_name, tour.tour_max_capacity, tour.tour_name, tour.tour_number_of_rating, tour.tour_price, tour.tour_registration, tour.tour_schedule, tour.tour_star, tour.tour_start_date, tour.tour_starting_point, tour.tour_status, tour.tour_status_id, tour.tour_total_view, tour.tour_vehicle))
    }

    Surface(modifier = Modifier.fillMaxWidth().padding(bottom = BOTTOM_NAV_SPACE)) {
        val tours = remember { mutableStateOf<List<Tour>>(emptyList()) }
        VerticalScrollLayout(
            modifier = Modifier.fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(top=31.dp),
            ChildLayout(
                contentType = HomeScreenContents.HEADER_SECTION.name,
                content = {
                    homeHeader()
                }
            ),
            ChildLayout(
                contentType = HomeScreenContents.CATEGORY_VIEW_ALL.name,
                content = {
                   TitleWithViewAllItem("Location", "View All", R.drawable.arrow_forward)
                }
            ),
            ChildLayout(
                contentType = HomeScreenContents.CATEGORY_SECTION.name,
                content = {

                    loadLocationItems(list) {
                        location ->
                        when(location.id)  {
                            0 -> tours.value = list2
                            else -> tours.value = list2.filter { it.tour_location_id == location.id }
                        }
                    }
                }
            ),
            ChildLayout(
                contentType = HomeScreenContents.DESTINATION_LARGE_SECTION.name,
                content = {
                    loadTourLargeItems(list2) {
                        routeState.value = Route(
                            screen = Screen.DetailScreen(it),
                            prev = Screen.HomeScreen
                        )
                    }
                }
            ),
            ChildLayout(
                contentType = HomeScreenContents.DESTINATION_VIEW_ALL.name,
                content = {
                    TitleWithViewAllItem("Popular Destination", "View All", R.drawable.arrow_forward)
                }
            ),
            ChildLayout(
                contentType = HomeScreenContents.DESTINATION_SMALL_SECTION.name,
                items = list2,
                content = { item ->
                    LoadItemAfterSafeCast<Tour>(item) {
                        tourSmallItem(it) {
                            routeState.value = Route(
                                screen = Screen.DetailScreen(it),
                                prev = Screen.HomeScreen
                            )
                        }
                    }
                }
            )
        )
    }
}
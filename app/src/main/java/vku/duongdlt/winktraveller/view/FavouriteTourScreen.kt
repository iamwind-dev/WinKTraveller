package vku.duongdlt.winktraveller.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import vku.duongdlt.winktraveller.R
import vku.duongdlt.winktraveller.ViewModel.BookmarkViewModel
import vku.duongdlt.winktraveller.ViewModel.TourViewModel
import vku.duongdlt.winktraveller.ViewModel.UserViewModel


import vku.duongdlt.winktraveller.navigation.Route
import vku.duongdlt.winktraveller.component.LoadItemAfterSafeCast
import vku.duongdlt.winktraveller.component.tourSmallItem
import vku.duongdlt.winktraveller.data.FakeFavorites
import vku.duongdlt.winktraveller.model.Booking
import vku.duongdlt.winktraveller.model.Bookmark
import vku.duongdlt.winktraveller.model.Tour
import vku.duongdlt.winktraveller.model.User
import vku.duongdlt.winktraveller.navigation.Screen
import vku.duongdlt.winktraveller.util.BOTTOM_NAV_SPACE

@Composable
fun FavouriteTourScreen(routeState: MutableState<Route>,bookmarkViewModel: BookmarkViewModel,userViewModel: UserViewModel,tourViewModel: TourViewModel) {
    var user by remember { mutableStateOf<User?>(null) }
    var bookmarks by remember { mutableStateOf<List<Bookmark>>(emptyList()) }
    var tours by remember { mutableStateOf<Map<String, Tour>>(emptyMap()) }

    LaunchedEffect(Unit) {
        userViewModel.getCurrentUser { fetchedUser ->
            user = fetchedUser
            // Fetch bookmarks only after user is fetched
            if (user != null) {
                bookmarkViewModel.getBookmarkByUserId(user!!.id) { fetchedBookmarks ->
                    bookmarks = fetchedBookmarks
                    // Fetch tour for each bookmark
                    fetchedBookmarks.forEach { bookmark ->
                        tourViewModel.getTourByBookingtourId(bookmark.bookmark_tour_id) { fetchedTour ->
                            fetchedTour?.let { tour ->
                                tours = tours.toMutableMap().apply {
                                    put(bookmark.bookmark_tour_id, tour)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = BOTTOM_NAV_SPACE)
    ) {
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
                Column(modifier = Modifier.padding(horizontal = 16.dp)){
                if (bookmarks.isNotEmpty()) {
                    bookmarks.forEach { bookmark ->
                        val tour = tours[bookmark.bookmark_tour_id]
                        if (tour != null) {
                            LoadItemAfterSafeCast<Tour>(tour) {
                                user?.let { it1 ->
                                    tourSmallItem(it1, modifier = Modifier.fillMaxWidth(), it) {
                                        routeState.value = Route(
                                            screen = Screen.DetailScreen(it),
                                            prev = Screen.FavouriteTourScreen
                                        )

                                    }
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }
                    }
                }
            }
        }
    }}
}
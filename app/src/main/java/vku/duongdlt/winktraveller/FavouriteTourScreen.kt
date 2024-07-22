package vku.duongdlt.winktraveller

import androidx.compose.foundation.background
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
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


import vku.duongdlt.winktraveller.navigation.Route
import vku.duongdlt.winktraveller.component.LoadItemAfterSafeCast
import vku.duongdlt.winktraveller.component.tourSmallItem
import vku.duongdlt.winktraveller.data.FakeFavorites
import vku.duongdlt.winktraveller.model.Tour
import vku.duongdlt.winktraveller.navigation.Screen
import vku.duongdlt.winktraveller.util.BOTTOM_NAV_SPACE

@Composable
fun FavouriteTourScreen(routeState: MutableState<Route>) {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = BOTTOM_NAV_SPACE)) {
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
                FakeFavorites.favorites.forEach { item ->
                    LoadItemAfterSafeCast<Tour>(item) {
                        tourSmallItem(modifier = Modifier.fillMaxWidth(),it) {
                            routeState.value = Route(
                                screen = Screen.DetailScreen(it),
                                prev = Screen.FavouriteTourScreen
                            )

                        }

                    }
                }
            }
        }
    }
}
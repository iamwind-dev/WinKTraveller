package vku.duongdlt.winktraveller.navigation

import vku.duongdlt.winktraveller.model.Destination
import vku.duongdlt.winktraveller.model.Tour


sealed class Screen(val route: String) {
    object AllOrderScreen : Screen("all_order_screen")
    object LoginScreen : Screen("login_screen")
    object BookingScreen : Screen("booking_screen")
    data class DetailScreen(val tour: Tour) : Screen("detail_screen")
    object FavouriteTourScreen : Screen("favourite_tour_screen")
    object HomeScreen : Screen("home_screen")
    object FirstScreen : Screen("first_screen")
    object SearchScreen : Screen("search_screen")
    object SignUpScreen : Screen("order_screen")
    object ProfileScreen : Screen("profile_screen")

}


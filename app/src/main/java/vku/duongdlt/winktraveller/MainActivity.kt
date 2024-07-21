package vku.duongdlt.winktraveller

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import vku.duongdlt.winktraveller.ViewModel.LocationViewModel
import vku.duongdlt.winktraveller.ViewModel.TourViewModel
import vku.duongdlt.winktraveller.ViewModel.UserViewModel
import vku.duongdlt.winktraveller.component.BottomButtonBar

import vku.duongdlt.winktraveller.util.AnimateVisibility
import vku.duongdlt.winktraveller.navigation.Route
import vku.duongdlt.winktraveller.component.BottomMenuBar
import vku.duongdlt.winktraveller.component.Menu
import vku.duongdlt.winktraveller.component.menuItems

import vku.duongdlt.winktraveller.navigation.Screen
import vku.duongdlt.winktraveller.ui.theme.WinKTravellerTheme

class MainActivity : ComponentActivity() {
    private val locationViewModel: LocationViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()
    private val tourViewModel: TourViewModel by viewModels()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WinKTravellerTheme {
                WinKUIMain(userViewModel, locationViewModel, tourViewModel)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WinKUIMain(
    userViewModel: UserViewModel,
    locationViewModel: LocationViewModel,
    tourViewModel: TourViewModel
) {

    WinKTravellerTheme {
//    Surface(modifier = Modifier.fillMaxSize()) {
//        val navController = rememberNavController()
//        val navBackStackEntry by navController.currentBackStackEntryAsState()
//        Navigation(navController)
//    }
        var visible by remember { mutableStateOf(true) }
        val routeState = remember { mutableStateOf(Route(Screen.HomeScreen)) }
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val state = routeState.value.screen
//        val navController = rememberNavController()
//     val navBackStackEntry by navController.currentBackStackEntryAsState()
//   Navigation(navController)
            when (state) {
                is Screen.LoginScreen -> {
                    visible = true
                    LoginScreen(routeState = routeState,userViewModel = userViewModel)
                }

                is Screen.SignUpScreen -> {
                    visible = true
                    SignUpScreen(routeState = routeState, userViewModel = userViewModel)
                }


                is Screen.HomeScreen -> {
                    visible = true
                    HomeScreen(routeState = routeState, locationViewModel = locationViewModel, tourViewModel = tourViewModel)
                }

                is Screen.FavouriteTourScreen -> {
                    visible = true
                    FavouriteTourScreen(routeState = routeState)
                }

                is Screen.AllOrderScreen -> {
                    visible = true
                    CartScreen(routeState = routeState)
                }

                is Screen.ProfileScreen -> {
                    visible = true
                    ProfileScreen(routeState = routeState)
                }

                is Screen.DetailScreen -> {
                    visible = false
                    DetailScreen(
                        routeState = routeState,
                        tour = state.tour,
                        tourviewModel = tourViewModel
                    )
                }

                is Screen.BookingScreen -> {
                    visible = false
                    BookingScreen(
                        routeState = routeState,
                        onEvent = { /*TODO*/ },
                        onNavigateBack = { /*TODO*/ },
                        tour = state.tour
                    )
                }
                Screen.FirstScreen -> {
                    TODO()
                }
                Screen.SearchScreen -> {
                    TODO()
                }
                Screen.SignUpScreen -> {
                    TODO()
                }

                is Screen.DetailScreen -> TODO()
                is Screen.InforBookingScreen -> {
                    visible = false
                    InforBookingScreen(routeState = routeState,tour = state.tour)
                }
            }
//            if (routeState.value.screen !is Screen.LoginScreen && routeState.value.screen !is Screen.SignUpScreen && routeState.value.screen !is Screen.HomeScreen){
//                BottomButtonBar(route = routeState) {
//
//                }
//            }
            if (routeState.value.screen != Screen.LoginScreen && routeState.value.screen != Screen.SignUpScreen) {
                AnimateVisibility(
                    visible = visible,
                    modifier = Modifier
                        .wrapContentSize(Alignment.BottomStart)
                ) {
                    BottomMenuBar(menuItems = menuItems, route = routeState) {
                        when (it.item) {
                            Menu.HOME -> routeState.value = Route(screen = Screen.HomeScreen)
                            Menu.FAVORITE -> routeState.value =
                                Route(screen = Screen.FavouriteTourScreen)

                            Menu.CART -> routeState.value = Route(screen = Screen.AllOrderScreen)
                            Menu.PROFILE -> routeState.value = Route(screen = Screen.ProfileScreen)
                        }
                    }
                }
            }
        }
    }
}

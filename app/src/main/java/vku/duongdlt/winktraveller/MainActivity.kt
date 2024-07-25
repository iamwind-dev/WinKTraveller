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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.firebase.auth.FirebaseAuth
import vku.duongdlt.winktraveller.ViewModel.BookingViewModel
import vku.duongdlt.winktraveller.ViewModel.BookmarkViewModel
import vku.duongdlt.winktraveller.ViewModel.LocationViewModel

import vku.duongdlt.winktraveller.ViewModel.TourViewModel
import vku.duongdlt.winktraveller.ViewModel.UserViewModel

import vku.duongdlt.winktraveller.util.AnimateVisibility
import vku.duongdlt.winktraveller.navigation.Route
import vku.duongdlt.winktraveller.component.BottomMenuBar
import vku.duongdlt.winktraveller.component.Menu
import vku.duongdlt.winktraveller.component.menuItems

import vku.duongdlt.winktraveller.navigation.Screen
import vku.duongdlt.winktraveller.ui.theme.WinKTravellerTheme
import vku.duongdlt.winktraveller.view.BookingScreen
import vku.duongdlt.winktraveller.view.CartScreen
import vku.duongdlt.winktraveller.view.ConfirmationScreen
import vku.duongdlt.winktraveller.view.DetailScreen
import vku.duongdlt.winktraveller.view.FavouriteTourScreen
import vku.duongdlt.winktraveller.view.HomeScreen
import vku.duongdlt.winktraveller.view.InforBookingScreen
import vku.duongdlt.winktraveller.view.InformationScreen
import vku.duongdlt.winktraveller.view.LoginScreen
import vku.duongdlt.winktraveller.view.ProfileScreen
import vku.duongdlt.winktraveller.view.SignUpScreen
import vku.duongdlt.winktraveller.view.SplashScreen

class MainActivity : ComponentActivity() {
    private val locationViewModel: LocationViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()
    private val tourViewModel: TourViewModel by viewModels()
    private val bookingViewModel: BookingViewModel by viewModels()
    private val bookmarkViewModel: BookmarkViewModel by viewModels()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WinKTravellerTheme {
                WinKUIMain(userViewModel, locationViewModel, tourViewModel,bookingViewModel,bookmarkViewModel)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WinKUIMain(
    userViewModel: UserViewModel,
    locationViewModel: LocationViewModel,
    tourViewModel: TourViewModel,
    bookingViewModel: BookingViewModel,
    bookmarkViewModel: BookmarkViewModel
) {

    WinKTravellerTheme {
//    Surface(modifier = Modifier.fillMaxSize()) {
//        val navController = rememberNavController()
//        val navBackStackEntry by navController.currentBackStackEntryAsState()
//        Navigation(navController)
//    }
        var visible by remember { mutableStateOf(true) }

        // Khởi tạo routeState với giá trị mặc định
        val routeState = remember { mutableStateOf(Route(Screen.SplashScreen)) }
        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        // Cập nhật routeState dựa trên trạng thái đăng nhập của người dùng
        LaunchedEffect(currentUser) {
            if (currentUser != null) {
                routeState.value = Route(Screen.HomeScreen) // Nếu người dùng đã đăng nhập, điều hướng đến màn hình chính
            } else {
                routeState.value = Route(Screen.LoginScreen) // Nếu người dùng chưa đăng nhập, điều hướng đến màn hình đăng nhập
            }
        }

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
                    HomeScreen(routeState = routeState, locationViewModel = locationViewModel, tourViewModel = tourViewModel, userViewModel = userViewModel)
                }

                is Screen.FavouriteTourScreen -> {
                    visible = true
                    FavouriteTourScreen(routeState = routeState, bookmarkViewModel = bookmarkViewModel,userViewModel, tourViewModel = tourViewModel)
                }

                is Screen.AllOrderScreen -> {
                    visible = true
                    CartScreen(routeState = routeState,userViewModel = userViewModel,tourViewModel = tourViewModel,bookingViewModel = bookingViewModel)
                }

                is Screen.ProfileScreen -> {
                    visible = true
                    ProfileScreen(routeState = routeState,userViewModel)
                }

                is Screen.DetailScreen -> {
                    visible = false
                    DetailScreen(
                        routeState = routeState,
                        tour = state.tour,
                        tourviewModel = tourViewModel,

                    )
                }

                is Screen.BookingScreen -> {
                    visible = false
                    BookingScreen(
                        routeState = routeState,
                        onEvent = { /*TODO*/ },
                        onNavigateBack = { /*TODO*/ },
                        tour = state.tour,
                        userViewModel = userViewModel
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

                is Screen.InforBookingScreen -> {
                    visible = false
                    InforBookingScreen(routeState = routeState,tour = state.tour,booking = state.booking,user =state.user,userViewModel = userViewModel)
                }

                is Screen.ConfirmationScreen -> {
                    visible = false
                    ConfirmationScreen(routeState = routeState,booking = state.booking,tour = state.tour)
                }
                is Screen.SplashScreen -> {
                    visible = false
                    SplashScreen()
                }

                Screen.InformationScreen -> {
                    visible = false
                    InformationScreen(routeState = routeState,userViewModel = userViewModel)
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

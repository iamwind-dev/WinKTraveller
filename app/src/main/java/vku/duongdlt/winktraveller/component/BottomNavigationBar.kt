package vku.duongdlt.winktraveller.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import vku.duongdlt.winktraveller.util.AnimateVisibility
import vku.duongdlt.winktraveller.navigation.Route

import vku.duongdlt.winktraveller.R
import vku.duongdlt.winktraveller.model.MenuItem
import vku.duongdlt.winktraveller.navigation.Screen
import vku.duongdlt.winktraveller.component.PrimaryButton as PrimaryButton1

val menuItems = arrayListOf<MenuItem>().apply {
    add(MenuItem(Menu.HOME, "Home", R.drawable.menu_home, Screen.HomeScreen))
    add(MenuItem(Menu.FAVORITE, "Favorite", R.drawable.menu_fav,Screen.FavouriteTourScreen))
    add(MenuItem(Menu.CART, "Cart", R.drawable.menu_cart,Screen.AllOrderScreen))
    add(MenuItem(Menu.PROFILE, "Profile", R.drawable.menu_profile,Screen.ProfileScreen))
}

enum class Menu(index: Int) {
    HOME(0),
    FAVORITE(1),
    CART(2),
    PROFILE(3),
}

@Composable
private fun BottomMenuItem(
    menuItem: MenuItem,
    route: MutableState<Route>,
    onItemClicked: (MenuItem) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(34.dp).clickable { onItemClicked.invoke(menuItem) },
            painter = painterResource(id=menuItem.icon),
            contentDescription = menuItem.title,
            tint = colorResource(id=if (route.value.screen == menuItem.screen) R.color.TextColor else R.color.secondTextColor)
        )
        AnimateVisibility(
            visible = route.value.screen == menuItem.screen,
            modifier = Modifier
                .wrapContentSize(Alignment.BottomStart)
        ) {
            Divider(
                modifier = Modifier
                    .width(22.dp)
                    .padding(top = 4.dp)
                    .background(color = colorResource(R.color.TextColor), shape = RoundedCornerShape(16.dp)),
                thickness = 4.dp,
                color = colorResource(R.color.TextColor)
            )
        }
    }
}

@Composable
fun BottomMenuBar(
    modifier: Modifier = Modifier,
    route: MutableState<Route>,
    menuItems: List<MenuItem>,
    onItemClicked: (MenuItem) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colorResource(R.color.white),
                shape = RoundedCornerShape(size = 0.dp)
            ),
    ) {
        LazyRow(
            modifier = Modifier.fillMaxWidth().padding(vertical = 18.dp, horizontal = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            items(
                items = menuItems,
                itemContent = { item ->
                    BottomMenuItem(item,route) {
                        onItemClicked.invoke(it)
                    }
                }
            )
        }
    }
}

@Composable
fun BottomButtonBar(
    modifier: Modifier = Modifier,
    route: MutableState<Route>,
    onItemClicked: (MenuItem) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colorResource(R.color.white),
                shape = RoundedCornerShape(size = 0.dp)
            ),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 18.dp, horizontal = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PrimaryButton1(
                title = "Book Now",
                paddingValues = PaddingValues(
                    start = 25.dp,
                    top = 36.dp,
                    end = 25.dp,
                    bottom = 36.dp
                ),
                onClick = {route.value = Route(
                    screen = Screen.HomeScreen,
                    prev = Screen.HomeScreen
                )}
            )
        }
    }
}

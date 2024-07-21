package vku.duongdlt.winktraveller

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import vku.duongdlt.winktraveller.util.BOTTOM_NAV_SPACE

import vku.duongdlt.winktraveller.navigation.Route

@Composable
fun CartScreen(routeState: MutableState<Route>){
    Surface(modifier = Modifier.fillMaxWidth().padding(bottom = BOTTOM_NAV_SPACE)) {
        Text(
            modifier = Modifier.wrapContentSize(Alignment.Center),
            text = "Cart",
            color = colorResource(R.color.textColor),
            style = MaterialTheme.typography.titleLarge,
        )
    }
}
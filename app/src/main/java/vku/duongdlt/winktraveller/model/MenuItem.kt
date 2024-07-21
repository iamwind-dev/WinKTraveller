package vku.duongdlt.winktraveller.model

import vku.duongdlt.winktraveller.component.Menu
import dev.icerock.moko.resources.ImageResource
import vku.duongdlt.winktraveller.navigation.Screen

data class MenuItem(
    val item: Menu,
    val title: String,
    val icon: Int,
    val screen: Screen,
)
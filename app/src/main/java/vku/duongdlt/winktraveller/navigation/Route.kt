package vku.duongdlt.winktraveller.navigation

import vku.duongdlt.winktraveller.navigation.Screen

data class Route(val screen: Screen, var prev: Screen? = null)
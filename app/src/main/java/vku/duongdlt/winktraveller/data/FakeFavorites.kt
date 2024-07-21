package vku.duongdlt.winktraveller.data

import vku.duongdlt.winktraveller.model.Destination
import vku.duongdlt.winktraveller.model.Tour

object FakeFavorites {
    val favorites = mutableListOf<Tour>()

    fun checkFavorite(tour: Tour) = favorites.any { it == tour }
    fun addFavorite(tour: Tour) {
        favorites.add(tour)
    }

    fun removeFavorite(tour: Tour){
        if (checkFavorite(tour)) {
            favorites.remove(tour)
        }
    }
}
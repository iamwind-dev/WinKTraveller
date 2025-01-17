package vku.duongdlt.winktraveller.component

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import vku.duongdlt.winktraveller.util.ImageItem
import vku.duongdlt.winktraveller.R
import vku.duongdlt.winktraveller.ViewModel.BookmarkViewModel
import vku.duongdlt.winktraveller.ViewModel.UserViewModel
import vku.duongdlt.winktraveller.model.Bookmark
import vku.duongdlt.winktraveller.model.Destination
import vku.duongdlt.winktraveller.model.Tour
import vku.duongdlt.winktraveller.model.User
import vku.duongdlt.winktraveller.ui.theme.HeliaTheme
import vku.duongdlt.winktraveller.util.formatCurrency

@Composable
fun destinationSmallItem(
    destination: Destination,
    onItemClicked: (Destination) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(start = 16.dp, top = 12.dp, end = 16.dp)
            .clickable { onItemClicked.invoke(destination) }
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(12.dp),
                ambientColor = colorResource(id = R.color.primaryColor),
                spotColor = colorResource(id = R.color.primaryColor)
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id=R.color.white)),
        shape = RoundedCornerShape(12.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ImageItem(
                data = destination.thumbnail,
                modifier = Modifier
                    .width(95.dp)
                    .height(84.dp),
            )
            Column(
                modifier = Modifier.padding(start = 14.dp)
            ) {
                Text(
                    text = destination.title,
                    color = colorResource(id=R.color.textColor),
                    style = MaterialTheme.typography.titleMedium
                )
                Row(
                    modifier = Modifier.padding(top = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(R.drawable.ci_location),
                        contentDescription = null,
                        tint = colorResource(id=R.color.primaryColor)
                    )
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = destination.location,
                        color = colorResource(id=R.color.thirdTextColor),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
                Text(
                    modifier = Modifier.padding(top = 6.dp),
                    text = destination.description,
                    color = colorResource(id=R.color.secondTextColor),
                    style = MaterialTheme.typography.bodySmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
                Row(
                    modifier = Modifier.padding(top = 9.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = destination.price,
                        color = colorResource(id=R.color.textColor),
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = "/${destination.type}",
                        color = colorResource(id=R.color.secondTextColor),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}



@Composable
fun destinationLargeItem(
    destination: Destination,
    onItemClicked: (Destination) -> Unit
) {
    Box(
        modifier = Modifier
            .width(222.dp)
            .height(143.dp)
            .padding(start = 16.dp)
            .clickable { onItemClicked.invoke(destination) }
            .background(
                color = colorResource(id = R.color.categoryBgColor),
                shape = RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.BottomStart
    ) {
        ImageItem(destination.thumbnail)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = destination.title,
                    color = colorResource(id=R.color.white),
                    style = MaterialTheme.typography.titleSmall
                )
                Row(
                    modifier = Modifier.padding(top = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(R.drawable.ci_location),
                        contentDescription = null,
                        tint = colorResource(id=R.color.white)
                    )
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = destination.location,
                        color = colorResource(id=R.color.white),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            Column(
                modifier = Modifier.padding(top = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = destination.price,
                    color = colorResource(id=R.color.white),
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = "/${destination.type}",
                    color = colorResource(id=R.color.white),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun loadDestinationSmallItems(destinations: List<Destination>){
    LazyColumn(
        modifier = Modifier.padding(start = 0.dp, top = 22.dp, end = 0.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = destinations,
            itemContent = { destinationSmallItem(it,{}) }
        )
    }
}

@Composable
fun loadDestinationLargeItems(
    destinations: List<Destination>,
    onItemClicked: (Destination) -> Unit
){
    LazyRow(
        modifier = Modifier.padding(top = 22.dp, bottom = 22.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = destinations,
            itemContent = {
                destinationLargeItem(
                    destination = it,
                    onItemClicked = onItemClicked
                )
            }
        )
    }
}

@Composable
fun tourSmallItem(
    user: User,
    modifier: Modifier,
    tour: Tour,
    onItemClicked: (Tour) -> Unit,

) {
    Surface(
        modifier = modifier.height(IntrinsicSize.Min),
        shape = HeliaTheme.shapes.medium,
        shadowElevation = 2.dp,
        color = if (HeliaTheme.theme.isDark) HeliaTheme.colors.dark2 else HeliaTheme.colors.white,
        onClick = { onItemClicked.invoke(tour) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            ImageItem(
                data = tour.url,
                modifier = Modifier
                    .width(95.dp)
                    .height(84.dp),
            )
            Spacer(modifier = Modifier.width(16.dp))
            ListHotelCardDetails(
                modifier = Modifier.weight(1f),
                name = tour.tour_name,
                city = tour.tour_location_name,
                rating = tour.tour_location_id.toString(),
                reviews = tour.tour_includes
            )
            Spacer(modifier = Modifier.width(8.dp))

            val isFav = remember { mutableStateOf(false) }
            val bm = remember { mutableStateOf(false) }
            var bookmarkViewModel = BookmarkViewModel()
            val bookmarkId = remember { mutableStateOf("") }


            LaunchedEffect(tour, user) {
                if (user != null) {
                    bookmarkViewModel.isTourBookmarked(user.id, tour.tour_id) { bookmarked ->
                        isFav.value = bookmarked
                        println("isFav: $isFav")
                    }
                }
            }

            val bookmarkviewmodel = BookmarkViewModel()
            val userViewModel = remember { UserViewModel() }

            val context = LocalContext.current
            val bookmarkid = remember { mutableStateOf("") }
            var updatedBookmark : Bookmark

            ListHotelCardPriceAndBookmark(
                tour = tour,
                modifier = Modifier.fillMaxHeight(),
                pricePerNight = formatCurrency(tour.tour_price),
                bookmarked = isFav,
                onBookmarkClick = {
                    if (isFav.value) {
                        bookmarkviewmodel.deleteBookmark(bookmarkId = bookmarkid.value){
                                success ->
                            if (success) {
                                println("Bookmark deleted successfully")
                            } else {
                                println("Failed to delete bookmark")

                            }                        }
                        Toast.makeText(
                            context,
                            context.getString(R.string.complete),
                            Toast.LENGTH_SHORT
                        ).show()
                        isFav.value = false
                    } else {
                        updatedBookmark=Bookmark(
                            bookmark_tour_id = tour.tour_id,
                            bookmark_user_id = user?.id.toString(),
                        )
                        bookmarkviewmodel.addBookmark(updatedBookmark){
                                success, bookmarkId ->
                            if (success) {
                                println("Bookmark added with ID: $bookmarkId")
                                bookmarkid.value = bookmarkId.toString()
                            } else {
                                println("Failed to add bookmark")
                            }
                        }
                        Toast.makeText(
                            context,
                            context.getString(R.string.complete),
                            Toast.LENGTH_SHORT
                        ).show()
                        isFav.value = true
                    }
                }
            )
        }
    }
//    Card(
//        modifier = Modifier
//            .padding(start = 16.dp, top = 12.dp, end = 16.dp)
//            .clickable { onItemClicked.invoke(tour) }
//            .shadow(
//                elevation = 4.dp,
//                shape = RoundedCornerShape(12.dp),
//                ambientColor = colorResource(id = R.color.TextColor),
//                spotColor = colorResource(id = R.color.TextColor)
//            ),
//        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
//        colors = CardDefaults.cardColors(containerColor = colorResource(id=R.color.white)),
//        shape = RoundedCornerShape(12.dp),
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(12.dp),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            ImageItem(
//                data = tour.url,
//                modifier = Modifier
//                    .width(95.dp)
//                    .height(84.dp),
//            )
//            Column(
//                modifier = Modifier.padding(start = 14.dp)
//            ) {
//                Text(
//                    text = tour.tour_name,
//                    color = colorResource(id=R.color.textColor),
//                    style = HeliaTheme.typography.bodyLargeBold
//                )
//                Row(
//                    modifier = Modifier.padding(top = 6.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        modifier = Modifier.size(16.dp),
//                        painter = painterResource(R.drawable.ci_location),
//                        contentDescription = null,
//                        tint = colorResource(id=R.color.TextColor)
//                    )
//                    Text(
//                        modifier = Modifier.padding(start = 8.dp),
//                        text = tour.tour_location_name,
//                        color = colorResource(id=R.color.TextColor),
//                        style = HeliaTheme.typography.bodyLargeBold
//                    )
//                }
////                Text(
////                    modifier = Modifier.padding(top = 6.dp),
////                    text = tour.tour_description,
////                    color = colorResource(id=R.color.secondTextColor),
////                    style = MaterialTheme.typography.bodySmall,
////                    overflow = TextOverflow.Ellipsis,
////                    maxLines = 2
////                )
//                HtmlText(text = tour.tour_description, style = HeliaTheme.typography.bodyLargeRegular,maxLines = 2, fontSize = 14.sp)
//                Row(
//                    modifier = Modifier.padding(top = 9.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Text(
//                        text = tour.tour_price.toString(),
//                        color = colorResource(id=R.color.textColor),
//                        style = MaterialTheme.typography.titleSmall
//                    )
//                    Text(
//                        modifier = Modifier.padding(start = 4.dp),
//                        text = "/${tour.tour_duration}",
//                        color = colorResource(id=R.color.secondTextColor),
//                        style = MaterialTheme.typography.bodySmall
//                    )
//                }
//            }
//        }
//    }
}

@Composable
fun loadTourLargeItems(
    tours: List<Tour>,
    user: User,
    onItemClicked: (Tour) -> Unit,

){
    LazyRow(
        modifier = Modifier.padding(top = 22.dp, bottom = 22.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = tours,
            itemContent = {
                tourLargeItem(
                    tour = it,
                    onItemClicked = onItemClicked,
                    user = user
                )
            }
        )
    }
}

@Composable
fun tourLargeItem(
    modifier: Modifier = Modifier,
    tour: Tour,
    onItemClicked: (Tour) -> Unit,
    user: User
) {
    val bookmark:Bookmark
    Surface(
        modifier = modifier.size(width = 300.dp, height = 400.dp).padding(start = 16.dp),
        shape = HeliaTheme.shapes.extraLarge,
        shadowElevation = 2.dp,
        onClick = {onItemClicked.invoke(tour)}
    ) {
        ImageItem(tour.url)
//        AsyncImage(
//            modifier = Modifier.fillMaxSize(),
//            model = imageUrl,
//            contentDescription = name,
//            contentScale = ContentScale.Crop
//        )
        Column {
            Chip(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(32.dp),
                toggled = true,
                onClick = { },
                text = tour.tour_location_id.toString(),
                leadingIconResId = R.drawable.ic_star,
                chipSize = ChipSizeValues.Small
            )
            Spacer(modifier = Modifier.weight(1f))
            val isFav = remember { mutableStateOf(false) }
            var bookmarkViewModel = BookmarkViewModel()
            val bookmarkId = remember { mutableStateOf("") }


            LaunchedEffect(tour, user) {
                if (user != null) {
                    bookmarkViewModel.isTourBookmarked(user.id, tour.tour_id) { bookmarked ->
                        isFav.value = bookmarked
                        println("isFav: $isFav")
                    }
                }
            }

            val bookmarkviewmodel = BookmarkViewModel()
            val userViewModel = remember { UserViewModel() }
            var user by remember { mutableStateOf<User?>(null) }
            LaunchedEffect(Unit) {
                userViewModel.getCurrentUser {
                    user = it
                }
            }
            val context = LocalContext.current
                val bookmarkid = remember { mutableStateOf("") }
                var updatedBookmark : Bookmark

            ImageHotelCardDetails(
                modifier = Modifier.fillMaxWidth(),
                name = tour.tour_name,
                city = tour.tour_location_name,
                pricePerNight = formatCurrency(tour.tour_price),
                bookmarked = isFav,
                tour = tour,
                onBookmarkClick = { if (isFav.value) {
                    bookmarkviewmodel.deleteBookmark(bookmarkId = bookmarkid.value){
                            success ->
                        if (success) {
                            println("Bookmark deleted successfully")
                        } else {
                            println("Failed to delete bookmark")

                        }                        }
                    Toast.makeText(
                        context,
                        context.getString(R.string.complete),
                        Toast.LENGTH_SHORT
                    ).show()
                    isFav.value = false
                } else {
                    isFav.value = true
                    updatedBookmark=Bookmark(
                        bookmark_tour_id = tour.tour_id,
                        bookmark_user_id = user?.id.toString(),
                    )
                    bookmarkviewmodel.addBookmark(updatedBookmark){
                            success, bookmarkId ->
                        if (success) {
                            println("Bookmark added with ID: $bookmarkId")
                            bookmarkid.value = bookmarkId.toString()
                        } else {
                            println("Failed to add bookmark")
                        }
                    }
                    Toast.makeText(
                        context,
                        context.getString(R.string.complete),
                        Toast.LENGTH_SHORT
                    ).show()
                } }
            )
        }

    }
//    Box(
//        modifier = Modifier
//            .width(222.dp)
//            .height(143.dp)
//            .padding(start = 16.dp)
//            .clickable { onItemClicked.invoke(tour) }
//            .background(
//                color = colorResource(id = R.color.categoryBgColor),
//                shape = RoundedCornerShape(10.dp)
//            ),
//        contentAlignment = Alignment.BottomStart
//    ) {
//        ImageItem(tour.url)
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 14.dp, vertical = 12.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Column {
//                Text(
//                    text = tour.tour_name,
//                    color = colorResource(id=R.color.white),
//                    style = MaterialTheme.typography.titleSmall
//                )
//                Row(
//                    modifier = Modifier.padding(top = 4.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        modifier = Modifier.size(16.dp),
//                        painter = painterResource(R.drawable.ci_location),
//                        contentDescription = null,
//                        tint = colorResource(id=R.color.white)
//                    )
//                    Text(
//                        modifier = Modifier.padding(start = 8.dp),
//                        text = tour.tour_location_name,
//                        color = colorResource(id=R.color.white),
//                        style = MaterialTheme.typography.bodySmall
//                    )
//                }
//            }
//            Column(
//                modifier = Modifier.padding(top = 4.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(
//                    text = tour.tour_price.toString(),
//                    color = colorResource(id=R.color.white),
//                    style = MaterialTheme.typography.titleSmall
//                )
//                Text(
//                    text = "/${tour.tour_duration}",
//                    color = colorResource(id=R.color.white),
//                    style = MaterialTheme.typography.bodySmall
//                )
//            }
//        }
//    }
}

@Composable
fun TourSmallItem(
    tour: Tour,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.height(IntrinsicSize.Min),
        shape = HeliaTheme.shapes.medium,
        shadowElevation = 2.dp,
        color = if (HeliaTheme.theme.isDark) HeliaTheme.colors.dark2 else HeliaTheme.colors.white,

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            ImageItem(
                data = tour.url,
                modifier = Modifier
                    .width(95.dp)
                    .height(84.dp),
            )
            Spacer(modifier = Modifier.width(16.dp))
            ListHotelCardDetails(
                modifier = Modifier.weight(1f),
                name = tour.tour_name,
                city = tour.tour_location_name,
                rating = tour.tour_location_id.toString(),
                reviews = tour.tour_includes
            )
            Spacer(modifier = Modifier.width(8.dp))
            val isFav = remember { mutableStateOf(false) }
            ListHotelCardPriceAndBookmark(
                tour = tour,
                modifier = Modifier.fillMaxHeight(),
                pricePerNight = formatCurrency(tour.tour_price),
                bookmarked = isFav,
                onBookmarkClick = { if (isFav.value) {

                    isFav.value = false
                } else {

                    isFav.value = true
                } }
            )
        }
    }
}




package vku.duongdlt.winktraveller.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vku.duongdlt.winktraveller.util.ImageItem
import vku.duongdlt.winktraveller.R
import vku.duongdlt.winktraveller.model.Destination
import vku.duongdlt.winktraveller.model.Tour
import vku.duongdlt.winktraveller.ui.theme.HeliaTheme

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
    tour: Tour,
    onItemClicked: (Tour) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(start = 16.dp, top = 12.dp, end = 16.dp)
            .clickable { onItemClicked.invoke(tour) }
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(12.dp),
                ambientColor = colorResource(id = R.color.TextColor),
                spotColor = colorResource(id = R.color.TextColor)
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
                data = tour.url,
                modifier = Modifier
                    .width(95.dp)
                    .height(84.dp),
            )
            Column(
                modifier = Modifier.padding(start = 14.dp)
            ) {
                Text(
                    text = tour.tour_name,
                    color = colorResource(id=R.color.textColor),
                    style = HeliaTheme.typography.bodyLargeBold
                )
                Row(
                    modifier = Modifier.padding(top = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(R.drawable.ci_location),
                        contentDescription = null,
                        tint = colorResource(id=R.color.TextColor)
                    )
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = tour.tour_location_name,
                        color = colorResource(id=R.color.TextColor),
                        style = HeliaTheme.typography.bodyLargeBold
                    )
                }
//                Text(
//                    modifier = Modifier.padding(top = 6.dp),
//                    text = tour.tour_description,
//                    color = colorResource(id=R.color.secondTextColor),
//                    style = MaterialTheme.typography.bodySmall,
//                    overflow = TextOverflow.Ellipsis,
//                    maxLines = 2
//                )
                HtmlText(text = tour.tour_description, style = HeliaTheme.typography.bodyLargeRegular,maxLines = 2, fontSize = 14.sp)
                Row(
                    modifier = Modifier.padding(top = 9.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = tour.tour_price.toString(),
                        color = colorResource(id=R.color.textColor),
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = "/${tour.tour_duration}",
                        color = colorResource(id=R.color.secondTextColor),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@Composable
fun loadTourLargeItems(
    tours: List<Tour>,
    onItemClicked: (Tour) -> Unit
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
                    onItemClicked = onItemClicked
                )
            }
        )
    }
}

@Composable
fun tourLargeItem(
    tour: Tour,
    onItemClicked: (Tour) -> Unit
) {
    Box(
        modifier = Modifier
            .width(222.dp)
            .height(143.dp)
            .padding(start = 16.dp)
            .clickable { onItemClicked.invoke(tour) }
            .background(
                color = colorResource(id = R.color.categoryBgColor),
                shape = RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.BottomStart
    ) {
        ImageItem(tour.url)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = tour.tour_name,
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
                        text = tour.tour_location_name,
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
                    text = tour.tour_price.toString(),
                    color = colorResource(id=R.color.white),
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = "/${tour.tour_duration}",
                    color = colorResource(id=R.color.white),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun TourSmallItem(
    tour: Tour
) {
    Card(
        modifier = Modifier
            .padding(start = 16.dp, top = 12.dp, end = 16.dp)
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
                data = tour.url,
                modifier = Modifier
                    .width(95.dp)
                    .height(84.dp),
            )
            Column(
                modifier = Modifier.padding(start = 14.dp)
            ) {
                Text(
                    text = tour.tour_name,
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
                        text = tour.tour_location_name,
                        color = colorResource(id=R.color.thirdTextColor),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
//                Text(
//                    modifier = Modifier.padding(top = 6.dp),
//                    text = tour.tour_description,
//                    color = colorResource(id=R.color.secondTextColor),
//                    style = MaterialTheme.typography.bodySmall,
//                    overflow = TextOverflow.Ellipsis,
//                    maxLines = 2
//                )
                HtmlText(text = tour.tour_description, maxLines = 2, fontSize = 14.sp)
                Row(
                    modifier = Modifier.padding(top = 9.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = tour.tour_price.toString(),
                        color = colorResource(id=R.color.textColor),
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = "/${tour.tour_duration}",
                        color = colorResource(id=R.color.secondTextColor),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}
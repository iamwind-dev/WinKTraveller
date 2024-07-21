package vku.duongdlt.winktraveller.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import vku.duongdlt.winktraveller.R
import vku.duongdlt.winktraveller.model.Category
import vku.duongdlt.winktraveller.model.Location
import vku.duongdlt.winktraveller.model.Tour

@Composable
fun categoryItem(category: Category, onItemClicked: (Category) -> Unit) {
    Box(
        modifier = Modifier
            .clickable { onItemClicked.invoke(category) }
            .background(
                color = colorResource(id= R.color.categoryBgColor),
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 9.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
//            Image(
//                modifier = Modifier.width(17.dp).height(20.dp),
//                painter = painterResource(id=category.location_image_url),
//                contentDescription = null
//            )
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = category.location_name,
                color = colorResource(id=R.color.secondTextColor),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun loadCategoryItems(categories: List<Category>, onItemClicked: (Category) -> Unit){
    LazyRow(
        modifier = Modifier.padding( start = 16.dp, top = 22.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = categories,
            itemContent = { categoryItem(it , onItemClicked) }
        )
    }
}

@Composable
fun locationItem(location: Location, onItemClicked: (Location) -> Unit) {
    Box(
        modifier = Modifier
            .clickable { onItemClicked.invoke(location) }
            .background(
                color = colorResource(id= R.color.categoryBgColor),
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 9.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
//            Image(
//                modifier = Modifier.width(17.dp).height(20.dp),
//                painter = painterResource(id=category.location_image_url),
//                contentDescription = null
//            )
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = location.location_name,
                color = colorResource(id=R.color.secondTextColor),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


@Composable
fun loadLocationItems(locations: List<Location>, onItemClicked: (Location) -> Unit){
    LazyRow(
        modifier = Modifier.padding( start = 16.dp, top = 22.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = locations,
            itemContent = { locationItem(it , onItemClicked) }
        )
    }
}


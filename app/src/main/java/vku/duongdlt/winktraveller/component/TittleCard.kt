package vku.duongdlt.winktraveller.component

import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vku.duongdlt.winktraveller.R
import vku.duongdlt.winktraveller.ui.theme.WinKTravellerTheme
import vku.duongdlt.winktraveller.util.ImageItem

@Composable
fun TitleWithViewAllItem(title: String, label: String, icon: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            color = colorResource(id=R.color.textColor),
            style = MaterialTheme.typography.titleLarge
        )
        Row(
            modifier = Modifier.padding(top = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                color = colorResource(id=R.color.thirdTextColor),
                style = MaterialTheme.typography.labelSmall
            )
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .padding(start = 8.dp),
                painter = painterResource(id= icon),
                contentDescription = null,
                tint = colorResource(id=R.color.primaryColor)
            )
        }
    }
}

@Composable
fun TitleWithReview(title: String, label: String, icon: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 36.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            color = colorResource(id=R.color.textColor),
            style = MaterialTheme.typography.titleLarge
        )
        Box(
            modifier = Modifier
                .background(color = colorResource(id=R.color.reviewBodyBg), shape = RoundedCornerShape(4.dp))
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(14.dp),
                    painter = painterResource(icon),
                    contentDescription = null,
                    tint = colorResource(id=R.color.yellow)
                )
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = label,
                    color = colorResource(id=R.color.secondTextColor),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun InformationCard(){
    Card(
        modifier = Modifier
            .padding(start = 16.dp, top = 12.dp, end = 16.dp)
//            .clickable { onItemClicked.invoke(destination) }
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
            Row {


            Image(
                painter = painterResource(id = R.drawable.profile_icon),
                modifier = Modifier
                    .padding(start = 50.dp)
                    .width(95.dp)
                    .height(84.dp),
                contentDescription = null
            )
                Column(modifier = Modifier.padding(start=30.dp)) {


                    Text(

                        text = "Thai Duong",
                        color = colorResource(id = R.color.textColor),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Row(
                        modifier = Modifier.padding(top = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(R.drawable.menu_profile),
                            contentDescription = null,
                            tint = colorResource(id = R.color.primaryColor)
                        )
                        Text(
                            modifier = Modifier.padding(start = 8.dp),
                            text = "Thai Duong",
                            color = colorResource(id = R.color.thirdTextColor),
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                }
            }
        }
    }
    Card(
        modifier = Modifier
            .padding(start = 16.dp, top = 12.dp, end = 16.dp)
//            .clickable { onItemClicked.invoke(destination) }
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
            Row {
                Image(
                    painter = painterResource(id = R.drawable.back),
                    modifier = Modifier
                        .width(95.dp)
                        .height(50.dp),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Sign Out",
                    color = colorResource(id=R.color.textColor),
                    style = MaterialTheme.typography.titleMedium,

                    )
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    WinKTravellerTheme {
        TitleWithViewAllItem(title="a", label="a", icon=R.drawable.humberg_icon)
    }
}

package vku.duongdlt.winktraveller.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vku.duongdlt.winktraveller.ViewModel.UserViewModel
import vku.duongdlt.winktraveller.component.TripitacaRoundedInputField
import vku.duongdlt.winktraveller.component.datepicker.FullScreenDatePickerDialog
import vku.duongdlt.winktraveller.model.Booking
import vku.duongdlt.winktraveller.model.CountAction
import vku.duongdlt.winktraveller.model.Tour
import vku.duongdlt.winktraveller.model.User
import vku.duongdlt.winktraveller.navigation.Route


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(
routeState: MutableState<Route>,
    onEvent: () -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,tour: Tour,userViewModel: UserViewModel
) {
    val booking = Booking()
    val user = User()
    FullScreenDatePickerDialog(open = true, onDismiss = { /*TODO*/ }, onSelect = {}, routeState = routeState, tour = tour,booking = booking,user = user)
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun DateInput(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    onDateSelected:  () -> Unit
) {


    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        TripitacaRoundedInputField(
            value = value,
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth(),
            readOnly = true
        )
    }
}

@Composable
private fun PeopleCountItem(
    title: String,
    description: String,
    value: Int,
    onAction: (CountAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = description,
                fontSize = 12.sp
            )
        }
        CountComponent(
            value = value,
            onAction = onAction
        )
    }
}

@Composable
fun CountComponent(
    value: Int,
    onAction: (CountAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant,
                    shape = RoundedCornerShape(100)
                )
                .size(25.dp),
            onClick = { onAction(CountAction.SUBTRACT) }
        ) {
            Icon(
                imageVector = Icons.Rounded.Remove,
                contentDescription = "stringResource(R.string.reduce_count)",
                modifier = Modifier
                    .size(15.dp)
            )
        }
        Text(
            text = value.toString(),
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(horizontal = 10.dp)

        )
        IconButton(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant,
                    shape = RoundedCornerShape(100)
                )
                .size(25.dp),
            onClick = { onAction(CountAction.Add) }
        ) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = "stringResource(R.string.reduce_count)",
                modifier = Modifier
                    .size(15.dp)
            )
        }
    }
}



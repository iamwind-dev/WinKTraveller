package vku.duongdlt.winktraveller.component.datepicker

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vku.duongdlt.winktraveller.R
import vku.duongdlt.winktraveller.component.BookingHeader
import vku.duongdlt.winktraveller.component.FullScreenDialog
import vku.duongdlt.winktraveller.component.PrimaryButton
import vku.duongdlt.winktraveller.component.TourSmallItem
import vku.duongdlt.winktraveller.component.TripitacaRoundedInputField
import vku.duongdlt.winktraveller.model.Tour
import vku.duongdlt.winktraveller.navigation.Route
import vku.duongdlt.winktraveller.navigation.Screen
import vku.duongdlt.winktraveller.util.toMillis
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullScreenDatePickerDialog(
    open: Boolean,
    onDismiss: () -> Unit,
    onSelect: (date: LocalDate) -> Unit,
    initialSelectedDate: LocalDate? = null,
    routeState: MutableState<Route>,
    tour: Tour
) {
    var checkInDate by remember { mutableStateOf<LocalDate?>(initialSelectedDate) }
    var checkOutDate by remember { mutableStateOf<LocalDate?>(null) }
    var numberOfAdults by remember { mutableStateOf(1) }  // Added state for number of adults

    if (open) {
        FullScreenDialog(onDismissRequest = onDismiss) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
            ) {
                BookingHeader(routeState = routeState, tour = tour)
                    Row(){
                        Column (modifier = Modifier.padding(16.dp)){
                            TourSmallItem(tour = tour)
                        }

                    }
                Row(
                    modifier = Modifier
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    DateInput(
                        label = "Check In",
                        value = checkInDate?.toString() ?: "",
                        selectedDate = checkInDate,
                        onDateSelected = { date ->
                            checkInDate = date
                        },
                        modifier = Modifier.weight(1f)
                    )

                    DateInput(
                        label = "Check Out",
                        value = checkOutDate?.toString() ?: "",
                        selectedDate = checkOutDate,
                        onDateSelected = { date ->
                            checkOutDate = date
                        },
                        modifier = Modifier.weight(1f)
                    )
                }

                PeopleCountItem(
                    title = "Adults",
                    description = "2 Adults",
                    value = numberOfAdults,
                    onAction = { /* Increment/Decrement logic here */ },
                    onValueChange = { newValue -> numberOfAdults = newValue } // Update value on change
                )

                PrimaryButton(
                    title = "Continue",
                    paddingValues = PaddingValues(
                        start = 25.dp,
                        top = 36.dp,
                        end = 25.dp,
                        bottom = 36.dp
                    ),
                    onClick = {
                        routeState.value = Route(
                            screen = Screen.InforBookingScreen(tour),
                            prev = Screen.BookingScreen(tour)
                        )
                    }
                )
            }
        }
    }
}



@Composable
private fun PeopleCountItem(
    title: String,
    description: String,
    value: Int,
    onAction: () -> Unit,
    onValueChange: (Int) -> Unit,
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
                modifier = Modifier.padding(start = 40.dp),
                text = title,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                modifier = Modifier.padding(start = 40.dp),
                text = "$value Adults",
                fontSize = 12.sp
            )
        }
        CountComponent(
            value = value,
            onValueChange = { newValue ->
                onValueChange(newValue) // Notify value change
            },
            modifier = Modifier.padding(end = 16.dp)
        )
    }
}


@Composable
fun CountComponent(
    value: Int,
    onValueChange: (Int) -> Unit,
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
            onClick = { if (value > 0) onValueChange(value - 1) }
        ) {
            Icon(
                imageVector = Icons.Rounded.Remove,
                contentDescription = stringResource(R.string.app_name),
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
            onClick = { onValueChange(value + 1) }
        ) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = stringResource(R.string.app_name),
                modifier = Modifier
                    .size(15.dp)
            )
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DateInput(
    label: String,
    value: String,
    selectedDate: LocalDate?,
    onDateSelected: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    var openDialog by remember { mutableStateOf(false) }
    val displayedDate = selectedDate ?: LocalDate.now()
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        Row {
            Text(
                text = label,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Spacer(modifier = Modifier.width(100.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_calendar),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { openDialog = true }
            )
        }
        if (openDialog) {
            DatePickerDialog(
                open = openDialog,
                onDismiss = { openDialog = false },
                onSelect = { date ->
                    onDateSelected(date)
                    openDialog = false
                },
                initialSelectedDate = selectedDate
            )
        }

        TripitacaRoundedInputField(
            value = displayedDate.toString(),
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            readOnly = true
        )
    }
}

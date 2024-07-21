package vku.duongdlt.winktraveller.component.datepicker

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vku.duongdlt.winktraveller.R
import vku.duongdlt.winktraveller.component.BookingHeader
import vku.duongdlt.winktraveller.component.FullScreenDialog
import vku.duongdlt.winktraveller.component.PrimaryButton
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
    tour : Tour
) {
    val openDialog = remember { mutableStateOf(false) }

    val value = remember { mutableStateOf(1) }

    if (open) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = initialSelectedDate?.toMillis(),
        )
        FullScreenDialog(onDismissRequest = onDismiss) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
            ) {
                BookingHeader(routeState = routeState, tour = tour)
                DatePickerDialog(open = openDialog, onDismiss = { /*TODO*/ }, onSelect = { /*TODO*/ })
//                DatePicker(
//                    modifier = Modifier,
//                    state = datePickerState,
//                    colors = HeliaDatePickerDefaults.colors(),
//                    title = null,
//                    headline = null,
//                    showModeToggle = false
//                )

                PeopleCountItem(title = "Adults", description = "2 Adults", value = value.value, onAction = { /*TODO*/ })

                PrimaryButton(
                    title = "Calender",
                    paddingValues = PaddingValues(
                        start = 25.dp,
                        top = 36.dp,
                        end = 25.dp,
                        bottom = 36.dp,


                        ),
                    onClick = { openDialog.value = true }
                )

                PrimaryButton(
                    title = "Continue",
                    paddingValues = PaddingValues(
                        start = 25.dp,
                        top = 36.dp,
                        end = 25.dp,
                        bottom = 36.dp,


                    ),
                    onClick = {routeState.value = Route(
                        screen = Screen.InforBookingScreen,
                        prev = Screen.BookingScreen(tour)
                    )}
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
    modifier: Modifier = Modifier
) {
    val value1 = remember { mutableStateOf(1) }
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
                text = value1.value.toString()+" Adults",
                fontSize = 12.sp
            )
        }
        CountComponent(
            value = value,
            onAction = {   if(value1.value > 0) value1.value--   },
            modifier = Modifier.padding(end = 16.dp)
        )
    }
}

@Composable
fun CountComponent(
    value: Int,
    onAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    val value = remember { mutableStateOf(1) }
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
            onClick = { if(value.value > 0) value.value-- }
        ) {
            Icon(
                imageVector = Icons.Rounded.Remove,
                contentDescription = stringResource(R.string.app_name),
                modifier = Modifier
                    .size(15.dp)
            )
        }
        Text(
            text = value.value.toString(),
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
            onClick = { value.value++ }
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
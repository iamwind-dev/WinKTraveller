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
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import vku.duongdlt.winktraveller.navigation.Route
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
    routeState: MutableState<Route>
) {
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
                BookingHeader(routeState = routeState)
                DatePicker(
                    modifier = Modifier,
                    state = datePickerState,
                    colors = HeliaDatePickerDefaults.colors(),
                    title = null,
                    headline = null,
                    showModeToggle = false
                )

                PeopleCountItem(title = "Adults", description = "2 Adults", value = 1, onAction = { /*TODO*/ })

                PrimaryButton(
                    title = "Continue",
                    paddingValues = PaddingValues(
                        start = 25.dp,
                        top = 36.dp,
                        end = 25.dp,
                        bottom = 36.dp
                    )
                ) {

                }
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
                text = description,
                fontSize = 12.sp
            )
        }
        CountComponent(
            value = value,
            onAction = onAction,
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
            onClick = { onAction() }
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
            onClick = { onAction() }
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
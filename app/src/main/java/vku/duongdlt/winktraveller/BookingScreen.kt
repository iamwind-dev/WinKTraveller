package vku.duongdlt.winktraveller

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import vku.duongdlt.winktraveller.component.TripitacaDateRangePicker
import vku.duongdlt.winktraveller.component.TripitacaPrimaryButton
import vku.duongdlt.winktraveller.component.TripitacaRoundedInputField
import vku.duongdlt.winktraveller.component.TripitacaTopAppBar
import vku.duongdlt.winktraveller.component.datepicker.DatePickerDialog
import vku.duongdlt.winktraveller.component.datepicker.FullScreenDatePickerDialog
import vku.duongdlt.winktraveller.model.CountAction
import vku.duongdlt.winktraveller.model.Tour
import vku.duongdlt.winktraveller.navigation.Route
import vku.duongdlt.winktraveller.ui.theme.WinKTravellerTheme
import vku.duongdlt.winktraveller.util.DateUtils.formatDate
import vku.duongdlt.winktraveller.util.toLocalDate


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(
routeState: MutableState<Route>,
    onEvent: () -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,tour: Tour
) {
    FullScreenDatePickerDialog(open = true, onDismiss = { /*TODO*/ }, onSelect = {}, routeState = routeState, tour = tour)

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



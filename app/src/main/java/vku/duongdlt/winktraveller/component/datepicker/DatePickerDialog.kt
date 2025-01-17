package vku.duongdlt.winktraveller.component.datepicker

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import vku.duongdlt.winktraveller.component.Dialog
import vku.duongdlt.winktraveller.ui.theme.HeliaTheme
import vku.duongdlt.winktraveller.ui.theme.LoginSocialButtonRippleTheme
import vku.duongdlt.winktraveller.util.toLocalDate
import vku.duongdlt.winktraveller.util.toMillis
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(
    open: Boolean,
    onDismiss: () -> Unit,
    onSelect: (date: LocalDate) -> Unit,
    initialSelectedDate: LocalDate? = null
) {
    if (open) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = initialSelectedDate?.toMillis()
        )
        MaterialTheme(
            colorScheme = MaterialTheme.colorScheme.copy(
                onSurfaceVariant = MaterialTheme.colorScheme.onSurfaceVariant
            )
        ) {
            CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant) {
                Dialog(
                    onDismissRequest = onDismiss,
                    properties = DialogProperties(usePlatformDefaultWidth = false)
                ) {
                    Column(modifier = Modifier) {
                        DatePicker(
                            modifier = Modifier,
                            state = datePickerState,
                            colors = DatePickerDefaults.colors(),
                            title = null,
                            headline = null,
                            showModeToggle = false
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(onClick = onDismiss) {
                                Text("Cancel")
                            }
                            Spacer(modifier = Modifier.width(32.dp))
                            Button(
                                enabled = datePickerState.selectedDateMillis != null,
                                onClick = {
                                    datePickerState.selectedDateMillis?.let { millis ->
                                        onSelect(millis.toLocalDate())
                                    }
                                }
                            ) {
                                Text("Confirm")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CancelButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(LocalRippleTheme provides LoginSocialButtonRippleTheme) {
        androidx.compose.material3.TextButton(
            modifier = modifier,
            onClick = onClick,
        ) {
            Text(
                text = "Cancel",
                color = if (HeliaTheme.theme.isDark) HeliaTheme.colors.white else HeliaTheme.colors.greyscale900
            )
        }
    }
}

@Composable
private fun ConfirmButton(
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(LocalRippleTheme provides LoginSocialButtonRippleTheme) {
        androidx.compose.material3.TextButton(
            modifier = modifier,
            onClick = onClick,
            enabled = enabled,
            colors = ButtonDefaults.textButtonColors(
                contentColor = HeliaTheme.colors.primary500,
                disabledContentColor = HeliaTheme.colors.greyscale500
            )
        ) {
            Text(text = "Confirm")
        }
    }
}
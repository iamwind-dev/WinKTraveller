package vku.duongdlt.winktraveller.component.datepicker

import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import vku.duongdlt.winktraveller.ui.theme.HeliaTheme

object HeliaDatePickerDefaults {
    val contentColor
        @Composable
        get() = if (HeliaTheme.theme.isDark) HeliaTheme.colors.white else HeliaTheme.colors.greyscale900

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun colors() = DatePickerDefaults.colors(
        containerColor = HeliaTheme.backgroundColor,
        weekdayContentColor = contentColor,
        yearContentColor = contentColor,
        currentYearContentColor = contentColor,
        selectedYearContentColor = HeliaTheme.colors.white,
        selectedYearContainerColor = HeliaTheme.colors.blue,
        subheadContentColor = contentColor,
        todayDateBorderColor = HeliaTheme.colors.blue,
        dayContentColor = contentColor,
        todayContentColor = contentColor,
        selectedDayContainerColor = HeliaTheme.colors.blue,
        selectedDayContentColor = HeliaTheme.colors.white
    )
}
package vku.duongdlt.winktraveller.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.toMillis(): Long {
    return this.atStartOfDay(ZoneId.of("UTC")).toInstant().toEpochMilli()
}

@RequiresApi(Build.VERSION_CODES.O)
fun Long.toLocalDate(): LocalDate {
    return Instant.ofEpochMilli(this).atZone(ZoneId.of("UTC")).toLocalDate()
}


@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate?.toUiString(): String =
    this?.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)) ?: ""


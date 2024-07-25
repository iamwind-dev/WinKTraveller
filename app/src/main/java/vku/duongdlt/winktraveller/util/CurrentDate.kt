package vku.duongdlt.winktraveller.util

import java.util.Calendar
import java.text.SimpleDateFormat
import java.util.Locale

fun getCurrentDate(): String {
    val calendar = Calendar.getInstance()
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return sdf.format(calendar.time)
}

fun getCurrentDateTime(): String {
    val calendar = Calendar.getInstance()
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return sdf.format(calendar.time)
}

fun main() {
    val currentDate = getCurrentDate()
    val currentDateTime = getCurrentDateTime()
    println("Current Date: $currentDate")  // Output: 2024-07-24 (example)
    println("Current Date and Time: $currentDateTime")  // Output: 2024-07-24 15:30:45 (example)
}

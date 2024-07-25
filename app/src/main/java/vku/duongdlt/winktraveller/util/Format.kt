package vku.duongdlt.winktraveller.util

import java.text.NumberFormat
import java.util.Locale

fun formatCurrency(value: Float): String {
    val format = NumberFormat.getCurrencyInstance(Locale("vi", "VN"))
    return format.format(value)
}
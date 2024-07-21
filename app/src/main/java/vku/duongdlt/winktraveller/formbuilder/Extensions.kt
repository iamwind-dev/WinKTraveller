package vku.duongdlt.winktraveller.formbuilder

fun String.isNumeric(): Boolean {
    return this.toIntOrNull()?.let { true } ?: false
}
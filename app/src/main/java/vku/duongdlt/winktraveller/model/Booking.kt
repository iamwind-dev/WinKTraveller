package vku.duongdlt.winktraveller.model

data class Booking(
    val booking_id: Int=0,
    val booking_custumer_name: String="",
    val booking_custumer_email: String="",
    val booking_custumer_phone: String="",
    val booking_tour_id: String="",
    val booking_capacity: Int=0,
    val booking_customer_address: String="",
    val booking_date: String="",
    val booking_total: Float=0.0f,
    val booking_status_id: Int=0,
    val booking_status_name: String="",
    val booking_user_id: String? ="",
    val booking_date_start: String="",
    val booking_date_end: String="",
)
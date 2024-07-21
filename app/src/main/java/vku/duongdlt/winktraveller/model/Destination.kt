package vku.duongdlt.winktraveller.model

import java.util.Date

data class Destination(
    val id: Int,
//    val tour_create_date: Date,
//    val tour_description: String,
//    val tour_duration: Int,
//    val tour_edit_date: Date,
//    val tour_end_date: Date,
//    val tour_highlights: String,
//    val tour_image_url: String,
//    val tour_includes: String,
//    val tour_introduction: String,
//    val tour_journey: String,
//    val tour_location_id: Int,
//    val tour_location_name: String,
//    val tour_max_capacity: Int,
//    val tour_name: String,
//    val tour_number_of_rating: Int,
//    val tour_price: Float,
//    val tour_registration: Int,
//    val tour_schedule: String,
//    val tour_star: Float,




    val thumbnail: String,
    val title: String,
    val description: String,
    val rating: Float,
    val location: String,
    val price: String,
    val type: String,
    val category: Category,
    val image: List<String>
)
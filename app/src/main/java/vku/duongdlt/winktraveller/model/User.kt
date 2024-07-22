package vku.duongdlt.winktraveller.model

data class User (
    val id: String="",
    val role_id: Int = 0,
    val user_address: String = "",
    val user_avatar: String = "",
    val user_name: String = "",
    val user_password: String = "",
    val user_phone: String = "",
    val user_email: String = "",
    val user_role: String = "",
    val image: String = "",
    val user_status: String = "",
    val user_status_id: Int = 0,
    val user_username: String = ""
)
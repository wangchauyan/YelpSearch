package idv.chauyan.yelpsearch.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Restaurant(
  val id: String,
  val alias: String,
  val name: String,
  @Json(name = "image_url")
  val restaurantLogo: String,
  val is_closed: Boolean,
  @Json(name = "url")
  val restaurantPage: String,
  val review_count: Int,
  val rating: Double,
  val phone: String,
  val isLoading : Boolean = false
)

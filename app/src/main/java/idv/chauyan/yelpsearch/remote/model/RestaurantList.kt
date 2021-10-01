package idv.chauyan.yelpsearch.remote.model

import com.squareup.moshi.Json

data class Restaurants(
  @Json(name = "businesses")
  val data: List<Restaurant>
)

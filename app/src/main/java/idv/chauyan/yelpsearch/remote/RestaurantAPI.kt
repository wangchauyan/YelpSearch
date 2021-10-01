package idv.chauyan.yelpsearch.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import idv.chauyan.yelpsearch.remote.model.Restaurants
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RestaurantAPI {

  @Headers("Authorization: Bearer 2ROaa2Rh9qu3WVTCms8FoVE4mSfHQHC7QJua95-kKT-PqzIlLSrs4tmHVdtdFw_66-JNfRiJmbCByHTvFNy5dQq-tpfS4FrPpupIzKlgELR3br-r5trpeFhrCRgwWnYx")
  @GET("v3/businesses/search")
  suspend fun getRestaurants(
    @Query("term") term: String,
    @Query("location") location: String,
    @Query("limit") limit: Int
  ): Restaurants

  companion object {
    fun create(debug: Boolean): RestaurantAPI {
      val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
      return Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(
          if (debug) "http://127.0.0.1:8000/"
          else "https://api.yelp.com/")
        .build()
        .create(RestaurantAPI::class.java)
    }
  }
}
package idv.chauyan.yelpsearch.data.repository

import idv.chauyan.yelpsearch.remote.RemoteDataImpl
import idv.chauyan.yelpsearch.remote.RestaurantAPI
import idv.chauyan.yelpsearch.remote.model.Restaurants

interface RemoteData {
  suspend fun getRestaurants(
    term: String,
    location: String,
    limit: Int
  ): Restaurants

  companion object {
    fun createRemoteDataSource(debug: Boolean): RemoteData {
      return RemoteDataImpl(RestaurantAPI.create(debug))
    }
  }
}
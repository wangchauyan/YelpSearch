package idv.chauyan.yelpsearch.remote

import idv.chauyan.yelpsearch.data.repository.RemoteData
import idv.chauyan.yelpsearch.remote.model.Restaurants

class RemoteDataImpl(
  private val api: RestaurantAPI
) : RemoteData {

  override suspend fun getRestaurants(
    term: String,
    location: String,
    limit: Int): Restaurants = api.getRestaurants(term, location, limit)
}
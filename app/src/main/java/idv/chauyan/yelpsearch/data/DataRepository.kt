package idv.chauyan.yelpsearch.data

import idv.chauyan.yelpsearch.data.repository.RemoteData
import idv.chauyan.yelpsearch.domain.DomainRepository
import idv.chauyan.yelpsearch.remote.model.Restaurants

class DataRepository(
  private val remoteData: RemoteData
) : DomainRepository {


  override suspend fun getRestaurants(
    term: String,
    location: String,
    limit: Int): Restaurants = remoteData.getRestaurants(term, location, limit)
}
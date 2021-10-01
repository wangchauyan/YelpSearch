package idv.chauyan.yelpsearch.domain

import idv.chauyan.yelpsearch.data.DataRepository
import idv.chauyan.yelpsearch.data.repository.RemoteData
import idv.chauyan.yelpsearch.remote.model.Restaurants

interface DomainRepository {

  /**
   * Domain layer - get restaurant list
   */
  suspend fun getRestaurants(
    term: String,
    location: String,
    limit: Int
  ): Restaurants

  companion object {
    fun create(debug: Boolean): DomainRepository {
      return DataRepository(RemoteData.createRemoteDataSource(debug))
    }
  }
}
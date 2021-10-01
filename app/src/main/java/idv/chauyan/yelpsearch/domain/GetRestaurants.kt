package idv.chauyan.yelpsearch.domain

class GetRestaurants(
  private val domainRepository: DomainRepository
) {

  suspend fun get(
    term: String,
    location: String,
    limit: Int
  ) = domainRepository.getRestaurants(term, location, limit)
}
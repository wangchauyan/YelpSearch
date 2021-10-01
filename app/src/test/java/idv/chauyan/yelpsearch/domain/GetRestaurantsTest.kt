package idv.chauyan.yelpsearch.domain

import idv.chauyan.yelpsearch.remote.model.Restaurants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetRestaurantsTest {
  @Mock
  private lateinit var domainRepository: DomainRepository

  @InjectMocks
  private lateinit var getRestaurants: GetRestaurants

  @ExperimentalCoroutinesApi
  @Test
  fun get() {
    runBlockingTest {

      val restaurants = Restaurants(
        data = listOf()
      )
      val term = "pizza"
      val location = "111 sutter st, san francisco, ca"
      val limit = 50

      Mockito.`when`(domainRepository.getRestaurants(term, location, limit)).thenReturn(restaurants)
      getRestaurants.get(term, location, limit)
      Mockito.verify(domainRepository).getRestaurants(term, location, limit)
    }
  }
}
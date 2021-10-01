package idv.chauyan.yelpsearch.remote

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
class RemoteDataImplTest {

  @Mock
  private lateinit var restaurantAPI: RestaurantAPI

  @InjectMocks
  private lateinit var remoteDataImpl: RemoteDataImpl

  @ExperimentalCoroutinesApi
  @Test
  fun getRestaurants() {
    runBlockingTest {
      val restaurants = Restaurants(
        data = listOf()
      )
      val term = "pizza"
      val location = "111 sutter st, san francisco, ca"
      val limit = 50

      Mockito.`when`(restaurantAPI.getRestaurants(term, location, limit)).thenReturn(restaurants)
      remoteDataImpl.getRestaurants(term, location, limit)
      Mockito.verify(restaurantAPI).getRestaurants(term, location, limit)
    }
  }
}
package idv.chauyan.yelpsearch.data

import idv.chauyan.yelpsearch.data.repository.RemoteData
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
class DataRepositoryTest {
  @Mock
  private lateinit var remoteData: RemoteData

  @InjectMocks
  private lateinit var dataRepository: DataRepository

  @ExperimentalCoroutinesApi
  @Test
  fun getAlbumsByKeyword() {
    runBlockingTest {

      val restaurants = Restaurants(
        data = listOf()
      )
      val term = "pizza"
      val location = "111 sutter st, san francisco, ca"
      val limit = 50

      Mockito.`when`(remoteData.getRestaurants(term, location, limit)).thenReturn(restaurants)
      dataRepository.getRestaurants(term, location, limit)
      Mockito.verify(remoteData).getRestaurants(term, location, limit)
    }
  }
}
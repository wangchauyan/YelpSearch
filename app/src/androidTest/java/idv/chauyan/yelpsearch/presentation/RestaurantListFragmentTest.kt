package idv.chauyan.yelpsearch.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth
import idv.chauyan.yelpsearch.R
import idv.chauyan.yelpsearch.mockserver.YelpWebServer
import idv.chauyan.yelpsearch.presentation.fragments.MockRestaurantListFragment
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class RestaurantListFragmentTest {

  private lateinit var yelpWebServer: YelpWebServer

  @get:Rule
  var instantExecutorRule = InstantTaskExecutorRule()

  @Before
  fun setUp() {
    yelpWebServer = YelpWebServer()
    yelpWebServer.onStart()
  }

  @After
  fun tearDown() {
    yelpWebServer.onDestroy()
  }

  @Test
  fun shouldRestaurantListView() {

    var restaurantListView: RecyclerView? = null
    val scenario = launchFragmentInContainer<MockRestaurantListFragment>()
    scenario.onFragment {
      restaurantListView = it.activity?.findViewById(R.id.list)
      Truth.assertThat(restaurantListView).isNotNull()
    }
    Espresso.onView(withId(R.id.list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    
    val itemCount = restaurantListView?.adapter?.itemCount
    Truth.assertThat(itemCount).isNotNull()
    Truth.assertThat(itemCount).isEqualTo(8)
  }
}
package idv.chauyan.yelpsearch.presentation

import android.os.Bundle
import android.os.Debug
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import idv.chauyan.yelpsearch.R
import idv.chauyan.yelpsearch.presentation.adapter.RestaurantListAdapter
import idv.chauyan.yelpsearch.remote.model.Restaurant

open class RestaurantListFragment : Fragment() {

  @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
  var debug: Boolean = false
  private val pageSize = 50

  private lateinit var viewModel: RestaurantListViewModel
  private lateinit var restaurantListAdapter: RestaurantListAdapter
  private lateinit var restaurantList: RecyclerView
  private lateinit var restaurantRefresher: SwipeRefreshLayout

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.restaurant_list_fragment, container, false)

    initViews(view)
    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    // setup view model and its observer
    setupViewModel()

    // fetch data
    viewModel.getRestaurantsWithPageSize(pageSize)
  }

  /**
   * initialize recyclerview and its adapter
   */
  private fun initViews(view: View) {
    restaurantList = view.findViewById(R.id.list)
    restaurantRefresher = view.findViewById(R.id.refresher)
    restaurantListAdapter = RestaurantListAdapter(arrayListOf())

    restaurantList.apply {
      layoutManager = LinearLayoutManager(activity)
      adapter = restaurantListAdapter
    }

    restaurantRefresher.apply {
      setOnRefreshListener {
        restaurantRefresher.isRefreshing = false
        viewModel.getRestaurantsWithPageSize(pageSize)
      }
    }
  }

  /**
   * setup observer for updating data
   */
  private fun setupViewModel() {
    viewModel = ViewModelProvider(this).get(RestaurantListViewModel::class.java)
    viewModel.initUseCases(debug)
    viewModel.getRestaurantsLiveData().observe(viewLifecycleOwner) { restaurants ->
      restaurantListAdapter.updateRestaurants(restaurants.data, true)
    }
  }

  companion object {

    fun newInstance() = RestaurantListFragment()
  }
}
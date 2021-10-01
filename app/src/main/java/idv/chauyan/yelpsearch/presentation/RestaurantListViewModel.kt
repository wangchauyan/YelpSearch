package idv.chauyan.yelpsearch.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import idv.chauyan.yelpsearch.domain.DomainRepository
import idv.chauyan.yelpsearch.domain.GetRestaurants
import idv.chauyan.yelpsearch.remote.model.Restaurants
import kotlinx.coroutines.launch

class RestaurantListViewModel : ViewModel() {

  private lateinit var getRestaurants: GetRestaurants
  private lateinit var restaurantsLiveData: MutableLiveData<Restaurants>

  fun initUseCases(debug: Boolean) {
    getRestaurants = GetRestaurants(DomainRepository.create(debug))

    restaurantsLiveData = MutableLiveData()
  }

  fun getRestaurantsLiveData() = restaurantsLiveData

  fun getRestaurantsWithPageSize(pageSize: Int) {
    viewModelScope.launch {
      val restaurants = getRestaurants.get("pizza", "111 sutter st, san francisco, ca", pageSize)
      restaurantsLiveData.postValue(restaurants)
    }
  }
}
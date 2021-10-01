package idv.chauyan.yelpsearch.presentation.fragments

import android.os.Bundle
import android.view.View
import idv.chauyan.yelpsearch.presentation.RestaurantListFragment

class MockRestaurantListFragment: RestaurantListFragment() {

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    this.debug = true
    super.onViewCreated(view, savedInstanceState)
  }
}
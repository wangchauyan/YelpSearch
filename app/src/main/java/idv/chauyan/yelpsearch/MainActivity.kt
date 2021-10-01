package idv.chauyan.yelpsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import idv.chauyan.yelpsearch.presentation.RestaurantListFragment

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    applyFragment(RestaurantListFragment.newInstance())
  }

  private fun applyFragment(fg: Fragment) {
    supportFragmentManager
      .beginTransaction()
      .apply {
        replace(R.id.fragmentContainer, fg)
        addToBackStack(null)
      }
      .commit()
  }
}
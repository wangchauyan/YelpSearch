package idv.chauyan.yelpsearch.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import idv.chauyan.yelpsearch.R
import idv.chauyan.yelpsearch.remote.model.Restaurant
import kotlinx.android.synthetic.main.restaurant_list_item.view.*
import kotlinx.android.synthetic.main.restaurant_loading.view.*

class RestaurantListAdapter(
  private val restaurants: ArrayList<Restaurant>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  enum class ItemType {
    TYPE_RESTAURANT,
    TYPE_LOADING
  }


  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return when (viewType) {
      ItemType.TYPE_RESTAURANT.ordinal -> {
        val view = LayoutInflater.from(parent.context)
          .inflate(R.layout.restaurant_list_item, parent, false)
        RestaurantItem(view)
      }
      else -> {
        val view = LayoutInflater.from(parent.context)
          .inflate(R.layout.restaurant_loading, parent, false)
        RestaurantLoading(view)
      }
    }
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    if (holder is RestaurantItem) {
      val restaurant = restaurants[position]
      Picasso.get()
        .load(restaurant.restaurantLogo)
        .into(holder.restaurantLogo)
      holder.restaurantName.text = restaurant.name
      holder.restaurantCategory.text = restaurant.phone
      holder.restaurantStatus.text = if (restaurant.is_closed) "closed" else "open"

    } else if (holder is RestaurantLoading) {
      holder.restaurantLoading.isIndeterminate = true
    }
  }

  override fun getItemCount(): Int = restaurants.size

  override fun getItemViewType(position: Int): Int {
    return if (restaurants[position].isLoading)
      ItemType.TYPE_LOADING.ordinal
    else
      ItemType.TYPE_RESTAURANT.ordinal
  }

  fun updateRestaurants(
    data: List<Restaurant>,
    refreshing: Boolean
  ) {
    if (refreshing) this.restaurants.clear()
    this.restaurants.addAll(data)
    notifyDataSetChanged()
  }

  fun showLoading() {
    restaurants.add(
      Restaurant(
        "",
        "",
        "",
        "",
        true,
        "",
        0,
        0.0,
        "",
        true
      )
    )
    notifyItemInserted(restaurants.size - 1)
  }

  fun dismissLoading() {
    restaurants.removeAt(restaurants.lastIndex)
    notifyItemRemoved(restaurants.size)
  }

  inner class RestaurantItem(private val view: View) : RecyclerView.ViewHolder(view) {

    val restaurantLogo: ImageView = view.restaurantLogo
    val restaurantName: TextView = view.restaurantName
    val restaurantCategory: TextView = view.restaurantCategory
    val restaurantStatus: TextView = view.restaurantStatus
  }

  inner class RestaurantLoading(private val view: View) : RecyclerView.ViewHolder(view) {

    val restaurantLoading: ProgressBar = view.restaurantLoading
  }
}

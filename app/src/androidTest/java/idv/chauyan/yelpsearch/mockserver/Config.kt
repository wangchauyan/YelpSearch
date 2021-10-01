package idv.chauyan.yelpsearch.mockserver

import androidx.test.platform.app.InstrumentationRegistry
import java.io.BufferedReader

/**
 * Provide mock data to mock web server
 */
object Config {

  /**
   * Return a sample restaurant list as a mock response body
   */
  fun getRestaurants(): String {
    val inputStream = InstrumentationRegistry
      .getInstrumentation()
      .context
      .assets
      .open("sample_restaurant.json")
    return inputStream.bufferedReader().use(BufferedReader::readText)
  }
}
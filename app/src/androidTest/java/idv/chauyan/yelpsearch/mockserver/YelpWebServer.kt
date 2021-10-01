package idv.chauyan.yelpsearch.mockserver

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest

/**
 * Mock Yelp web server for providing test sample
 */
class YelpWebServer {

  private lateinit var mockWebServer: MockWebServer

  fun onStart() {
    mockWebServer = MockWebServer()
    mockWebServer.dispatcher = APIDispatcher()
    mockWebServer.start(8000)
  }

  fun onDestroy() {
    mockWebServer.shutdown()
  }

  /**
   * Dispatcher for returning different mock data based on the request path
   */
  class APIDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
      
      // We haven't handled the network error yet
      return MockResponse().setResponseCode(200)
        .setBody(Config.getRestaurants())//MockResponse().setResponseCode(404)
    }
  }
}
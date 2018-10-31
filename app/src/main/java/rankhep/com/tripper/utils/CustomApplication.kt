package rankhep.com.tripper.utils

import android.app.Application

class CustomApplication : Application() {
    val TOURIST_CATEGORY = 1
    val RESTAURANT_CATEGORY = 2
    val PLAYING_CATEGORY = 3
    val SHOPPING_CATEGORY = 4
    val PARK_CATEGORY = 6
    val NIGHT_CATEGORY = 7
    val LOGIN_REQUEST_CODE = 333

    companion object {
        val TOURIST_CATEGORY = 1
        val RESTAURANT_CATEGORY = 2
        val PLAYING_CATEGORY = 3
        val SHOPPING_CATEGORY = 4
        val PARK_CATEGORY = 6
        val NIGHT_CATEGORY = 7
        val LOGIN_REQUEST_CODE = 333

        val REVIEW_DAY_VIEW_TYPE = 1
        val REVIEW_TOURIST_VIEW_TYPE = 0
    }
}

//nightPlaceBtn -> {
//    startSearchActivity(7)
//}
//playingBtn -> {
//    startSearchActivity(3)
//}
//restaurantBtn -> {
//    startSearchActivity(2)
//}
//touristBtn -> {
//    startSearchActivity(1)
//}
//shoppingBtn -> {
//    startSearchActivity(4)
//}
//parkBtn -> {
//    startSearchActivity(6)
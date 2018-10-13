package rankhep.com.tripper.model

import org.json.JSONObject
import java.io.Serializable

data class TasteSendModel(var days: Int,
                          var culture: Int,
                          var entertainment: Int,
                          var food: Int,
                          var region: Int,
                          var shopping: Int,
                          var tourist: Int,
                          var user: String,
                          var withChildren: Boolean,
                          val withElderly: Boolean) : Serializable {
    companion object {
        fun toJson(taste: TasteSendModel): JSONObject {
            val paramObject = JSONObject()

            taste.run {
                paramObject.put("days", days)
                paramObject.put("culture", culture)
                paramObject.put("entertainment", entertainment)
                paramObject.put("food", food)
                paramObject.put("region", region)
                paramObject.put("shopping", shopping)
                paramObject.put("tourist", tourist)
                paramObject.put("user", user)
                paramObject.put("withChildren", withChildren)
                paramObject.put("withElderly", withElderly)
            }
            return paramObject
        }
    }
}

//{
//    "culture": 0,
//    "days": 0,
//    "entertainment": 0,
//    "food": 0,
//    "region": 0,
//    "shopping": 0,
//    "tourist": 0,
//    "user": "string",
//    "withChildren": true,
//    "withElderly": true
//}
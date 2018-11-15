package rankhep.com.tripper.model

import com.google.gson.Gson

class UpdateModel(val beforePlacenum:Int,
                  val day:Int,
                  val newPlacenum:Int,
                  val seqnum:Int,
                  val username: String) {
    companion object {
        fun getJson(updateModel: UpdateModel):String{
            return Gson().toJson(updateModel)
        }
    }
}

//{
//    "beforePlacenum": 0,
//    "day": 0,
//    "newPlacenum": 0,
//    "seqnum": 0,
//    "username": "string"
//}
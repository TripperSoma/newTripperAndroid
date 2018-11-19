package rankhep.com.tripper.model

import com.google.gson.Gson

class TitleSendModel(val scheduleName:String,
                     val seqnum:Int,
                     val username:String) {
    companion object {
        fun toJson(titleModel: TitleSendModel): String {
            return Gson().toJson(titleModel)
        }
    }
}

//{
//    "scheduleName": "string",
//    "seqnum": 0,
//    "username": "string"
//}
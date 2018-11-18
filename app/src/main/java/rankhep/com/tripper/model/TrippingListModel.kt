package rankhep.com.tripper.model

import java.io.Serializable

data class TrippingListModel(var fromdate:String?,
                             var region:String,
                             val seqnum:Int,
                             var title:String,
                             var toDate:String?):Serializable
//{
//    "fromdate": "2018-11-18T04:22:06.776Z",
//    "region": "string",
//    "seqnum": 0,
//    "title": "string",
//    "toDate": "2018-11-18T04:22:06.776Z"
//}
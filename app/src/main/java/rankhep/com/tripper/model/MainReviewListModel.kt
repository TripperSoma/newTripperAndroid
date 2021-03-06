package rankhep.com.tripper.model

import java.io.Serializable

data class MainReviewListModel(var photo: String,
                               var reviewnum: Int,
                               var time: String,
                               var title: String,
                               var writer: String) : Serializable


//[
//{
//    "photo": "string",
//    "reviewnum": 0,
//    "time": {
//    "chronology": {
//    "calendarType": "string",
//    "id": "string"
//},
//    "dayOfMonth": 0,
//    "dayOfWeek": "MONDAY",
//    "dayOfYear": 0,
//    "hour": 0,
//    "minute": 0,
//    "month": "JANUARY",
//    "monthValue": 0,
//    "nano": 0,
//    "second": 0,
//    "year": 0
//},
//    "title": "string",
//    "writer": "string"
//}
//]
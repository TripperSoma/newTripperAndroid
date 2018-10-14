package rankhep.com.tripper.model

import java.io.Serializable

data class Place(val city: String,
                 val country: String,
                 val details: String,
                 val latitude: Double,
                 val longtitude: Double,
                 val name: String,
                 val photos: List<Photo>,
                 val place_num: Int,
                 val price: Int,
                 val rating: Double,
                 val thumb: Thumbnail,
                 val type: Int) : Serializable {}

//{
//    "city": "string",
//    "country": "string",
//    "details": "string",
//    "latitude": 0,
//    "longtitude": 0,
//    "name": "string",
//    "photos": {},
//    "place_num": 0,
//    "price": 0,
//    "rating": 0,
//    "thumb": {
//    "bucket": "string",
//    "thumbnum": 0
//},
//    "type": 0
//}
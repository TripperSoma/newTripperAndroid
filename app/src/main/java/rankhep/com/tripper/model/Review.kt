package rankhep.com.tripper.model

import java.io.Serializable

data class Review(var days: ArrayList<ReviewDay>,
                  var seqnum: Int,
                  var thumb: String,
                  var user: String):Serializable

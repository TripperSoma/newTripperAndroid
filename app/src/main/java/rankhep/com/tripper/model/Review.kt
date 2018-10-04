package rankhep.com.tripper.model

data class Review(var days: ArrayList<ReviewDay>,
                  var seqnum: Int,
                  var thumb: String,
                  var user: String)

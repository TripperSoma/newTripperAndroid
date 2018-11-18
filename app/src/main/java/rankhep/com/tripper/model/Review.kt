package rankhep.com.tripper.model

import java.io.Serializable

data class Review(var days: List<ReviewDay>,
                  var seqnum: Int,
                  var thumb: String,
                  var title:String,
                  var user: String) : Serializable{
    override fun toString(): String {
        return "Review(days=$days, seqnum=$seqnum, thumb='$thumb', title='$title', user='$user')"
    }
}


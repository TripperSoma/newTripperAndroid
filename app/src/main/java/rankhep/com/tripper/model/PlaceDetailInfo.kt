package rankhep.com.tripper.model

import java.io.Serializable

data class PlaceDetailInfo(val place: Place,
                           val reviewDTOS: List<ReviewDTO>) : Serializable {
}
package rankhep.com.tripper.model

import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

data class ScheduleModel(val place: Place,
                         val startTime: String) : Serializable{

    override fun toString(): String {
        return "ScheduleModel(place=$place, startTime='$startTime')"
    }

}
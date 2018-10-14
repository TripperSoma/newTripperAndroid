package rankhep.com.tripper.model

import java.io.Serializable

data class ScheduleModel(val place: Place,
                         val startTime: String) : Serializable
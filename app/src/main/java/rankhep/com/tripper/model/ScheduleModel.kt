package rankhep.com.tripper.model

import java.io.Serializable
import java.sql.Date

data class ScheduleModel(val place:Place,
                         val startTime: String):Serializable
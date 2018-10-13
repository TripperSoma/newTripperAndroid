package rankhep.com.tripper.model

import java.io.Serializable
import java.sql.Date

data class DayModel(val day:String,
                    val schedulelist:List<ScheduleModel>):Serializable {
}
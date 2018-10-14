package rankhep.com.tripper.model

import java.io.Serializable

data class DayModel(val day: String,
                    val schedulelist: List<ScheduleModel>) : Serializable {
}
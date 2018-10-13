package rankhep.com.tripper.model

import java.sql.Date


data class PlanModel(val seqnum:Int,
                     val fromdate:String,
                     val toDate:String,
                     val user:String,
                     val dayList:List<DayModel>)
package rankhep.com.tripper.model


data class PlanModel(val seqnum:Int,
                     val fromdate:String?,
                     val toDate:String?,
                     val user:String,
                     val dayList:List<DayModel>)
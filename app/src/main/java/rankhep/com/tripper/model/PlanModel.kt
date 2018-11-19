package rankhep.com.tripper.model

import com.google.gson.Gson
import java.io.Serializable


data class PlanModel(val seqnum: Int,
                     val fromdate: String,
                     val toDate: String,
                     val user: String,
                     var title:String,
                     val dayList: List<DayModel>) : Serializable {
    companion object {
        fun toJson(planModel: PlanModel): String {
            return Gson().toJson(planModel)
        }
    }
}
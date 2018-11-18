package rankhep.com.tripper.model

import java.util.*

data class ReviewDetail(var schedule:ScheduleModel,
                        var content:String,
                        var detailsnum:Int,
                        var photos:List<String>){
}

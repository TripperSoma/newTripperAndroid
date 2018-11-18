package rankhep.com.tripper.model

import java.util.*

data class ReviewDetail(var schedule:ScheduleModel,
                        var content:String,
                        var detailsnum:Int,
                        var photos:ArrayList<String>){
    override fun toString(): String {
        return "ReviewDetail(schedule=$schedule, content='$content', detailsnum=$detailsnum, photos=$photos)"
    }
}

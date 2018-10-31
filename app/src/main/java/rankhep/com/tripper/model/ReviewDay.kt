package rankhep.com.tripper.model

data class ReviewDay(var day: Int,
                     var detailDTOS: ArrayList<ReviewDetail>){
    override fun toString(): String {
        return "ReviewDay(day=$day, detailDTOS=$detailDTOS)"
    }
}
//{
//        "day": 0,
//        "detailDTOS": [
//        {
//            "content": "string",
//            "photos": [
//            "string"
//            ],
//            "schedulenum": 0
//        }
//        ]
//    }
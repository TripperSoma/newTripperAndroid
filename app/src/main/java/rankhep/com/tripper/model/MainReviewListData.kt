package rankhep.com.tripper.model

class MainReviewListData(var content: String,
                         var photolist: ArrayList<String>,
                         var rating: Float,
                         var schedulenum: Int,
                         var usernum: Int,
                         var userEmail:String,
                         var createTime:String)

//"userEmail": "string",
//"schedulenum": 0,
//"content": "나 홀로 꽉찬 2박 3일 여수 여행",
//"rating": 4.5,
//"photolist": [
//"https://s3.ap-northeast-2.amazonaws.com/tripper-bucket/2018/8/22/1534944508431-dummy2.jpg"
//],
//"createTime": "2018-08-22T13:28:29"
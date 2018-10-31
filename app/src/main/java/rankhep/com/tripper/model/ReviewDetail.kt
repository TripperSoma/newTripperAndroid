package rankhep.com.tripper.model

import java.util.*

data class ReviewDetail(var schedule:ScheduleModel,
                        var content:String,
                        var photos:List<String>){
    override fun toString(): String {
        return "ReviewDetail(schedule=$schedule, content='$content', photos=$photos)"
    }
}

//"schedule": {
//    "startTime": null,
//    "place": {
//        "place_num": 3,
//        "country": "한국",
//        "city": "부산시 서구 서대신동",
//        "name": "구덕산",
//        "rating": 4,
//        "details": "서구의 서대신동과 북구의 학장동, 사하구 당리동 까지 이어진 부산시의 대표적인 산이다. 해발 562m.\r\n태백산맥의 말단 금정산 줄기 끝 자락에 위치해 북동쪽으로는 엄광산, 남서쪽으로는 시약산에 이어져있다.\r\n\r\n기록에 따르면 19세기 말에는 병풍처럼 둘러져 있다 하여 사병산이라는 이름으로 불렸다고 한다.\r\n전형적인 노년산지의 모습을 띄고 있으며, 짙은 산림과 깊은 계곡이 발달해 있다.\r\n\r\n울창한 수림을 자랑하는 구덕산은 시민들의 등산코스로 각광 받고 있다. 부산 최초의 급수원인 구덕 수원지가 동쪽 산록에 위치하고 있으며, 구덕산 기슭에는 구덕골청소년수련원이 지어져 지역주민과 청소년들의 문화 요람의 장으로 애용되고 있다.\r\n\r\n이 외에도 휴식공간인 대신공원과 한 때 대규모 꽃 재배단지로 유명했던 꽃마을도 이 곳 구덕산에 있다",
//        "latitude": 35.12348,
//        "longtitude": 128.9923,
//        "price": 0,
//        "type": 1,
//        "thumb": {
//        "thumbnum": 2511,
//        "bucket": "http://post.phinf.naver.net/20150512_273/mtj232_1431404305859DpSiw_JPEG/mug_obj_201505121318271103.jpg"
//    },
//        "photos": [
//        {
//            "photonum": 2838,
//            "bucket": "http://post.phinf.naver.net/20150512_273/mtj232_1431404305859DpSiw_JPEG/mug_obj_201505121318271103.jpg"
//        }
//        ]
//    }
//},
//"content": "좋았음1",
//"photos": [
//"https://s3.ap-northeast-2.amazonaws.com/tripper-bucket/2018/10/26/thumb/1540539173227-test1.jpg",
//"https://s3.ap-northeast-2.amazonaws.com/tripper-bucket/2018/10/26/thumb/1540539251575-test1.jpg"
//
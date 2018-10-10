package rankhep.com.tripper.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_tourist_info.*
import rankhep.com.tripper.R
import java.util.*

class TouristInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tourist_info)

        gridImageList.setImageList(ArrayList<Any>().apply {
            add("https://t1.daumcdn.net/cfile/tistory/243B4F4253C640F426")
            add("https://t1.daumcdn.net/cfile/tistory/243B4F4253C640F426")
            add("https://t1.daumcdn.net/cfile/tistory/243B4F4253C640F426")
            add("https://t1.daumcdn.net/cfile/tistory/243B4F4253C640F426")
            add("https://t1.daumcdn.net/cfile/tistory/243B4F4253C640F426")
            add("https://t1.daumcdn.net/cfile/tistory/243B4F4253C640F426")
            add("https://t1.daumcdn.net/cfile/tistory/243B4F4253C640F426")
        })
    }
}

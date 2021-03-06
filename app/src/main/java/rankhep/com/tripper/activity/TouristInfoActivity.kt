package rankhep.com.tripper.activity

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_tourist_info.*
import kotlinx.android.synthetic.main.toolbar_tourist_info.*
import rankhep.com.dhlwn.utils.NetworkHelper
import rankhep.com.tripper.R
import rankhep.com.tripper.adapter.DetailInfoReviewAdapter
import rankhep.com.tripper.model.PlaceDetailInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TouristInfoActivity : AppCompatActivity() {

    private lateinit var placeDetailInfo: PlaceDetailInfo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tourist_info)

        initView()
    }

    private fun initView() {
        val placeNum = intent.getIntExtra("placeNum", 0)
        backBtn.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

        finishBtn.setOnClickListener {
            intent.putExtra("place", placeDetailInfo.place)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
        Log.e("asd", placeNum.toString())
        NetworkHelper.networkInstance.getPlaceDetailInfo(placeNum).enqueue(object : Callback<PlaceDetailInfo> {
            override fun onFailure(call: Call<PlaceDetailInfo>, t: Throwable) {
                t.printStackTrace()
                Log.e("asd", t.message)
            }

            override fun onResponse(call: Call<PlaceDetailInfo>, response: Response<PlaceDetailInfo>) {
                Log.e("asd", response.code().toString())
                if (response.code() == 200) {
                    response.body()?.let { placeDetailInfo = it }
                    setData(response.body())
                }
            }

        })
    }

    private fun setData(placeDetailInfo: PlaceDetailInfo?) {
        placeDetailInfo?.let {
            if (it.place.photos.isEmpty())
                gridImageList.visibility = View.GONE
            touristName.text = it.place.name
            touristAddress.text = it.place.city
            gridImageList.setImageList(it.place.photos)
            moreInfoText.text = it.place.details
            reviewList.adapter = DetailInfoReviewAdapter(it.reviewDTOS)
            reviewText.text = "리뷰 ${it.reviewDTOS.size}"
        }
    }
}

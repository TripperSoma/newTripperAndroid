package rankhep.com.tripper.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_hotel_info.*
import rankhep.com.dhlwn.utils.NetworkHelper
import rankhep.com.tripper.R
import rankhep.com.tripper.model.PlaceDetailInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HotelInfoActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_info)

        val placenum = intent.getIntExtra("placenum", 1)

        NetworkHelper.networkInstance.getPlaceDetailInfo(placenum).enqueue(object : Callback<PlaceDetailInfo> {
            override fun onFailure(call: Call<PlaceDetailInfo>, t: Throwable) {
                t.printStackTrace()
                Log.e("hotel", t.message)
            }

            override fun onResponse(call: Call<PlaceDetailInfo>, response: Response<PlaceDetailInfo>) {
                if (response.isSuccessful) {
                    response.body()?.let{
                        hotelLocationText.text =  it.place.city
                        hotelNameText.text = it.place.name
                        hotelImg.setImageList(it.place.photos)
                        infoText.text = it.place.details
                    }
                } else {
                    Log.e("hotel", "" + response.code())
                }
            }

        })

        reservationBtn.setOnClickListener {
            Toast.makeText(applicationContext, "서비스 준비중입니다.", Toast.LENGTH_SHORT).show()
        }
    }
}

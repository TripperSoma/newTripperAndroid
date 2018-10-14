package rankhep.com.tripper.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_calendar.*
import okhttp3.MediaType
import okhttp3.RequestBody
import rankhep.com.dhlwn.utils.NetworkHelper
import rankhep.com.tripper.R
import rankhep.com.tripper.adapter.CalenderListAdapter
import rankhep.com.tripper.model.Place
import rankhep.com.tripper.model.PlanModel
import rankhep.com.tripper.model.ScheduleModel
import rankhep.com.tripper.model.TasteSendModel
import rankhep.com.tripper.utils.CustomApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.util.*

class CalenderActivity : AppCompatActivity(), View.OnClickListener {
    val PLACE_SEARCH_REQUEST_CODE = 111
    private lateinit var customApplication: CustomApplication
    private lateinit var mAdapter: CalenderListAdapter
    private lateinit var planModel: PlanModel
    private var items: ArrayList<ScheduleModel> = ArrayList<ScheduleModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        customApplication = application as CustomApplication
        mAdapter = CalenderListAdapter(items)
        iniView()


        getMLData()
    }

    private fun getMLData() {
        val taste: TasteSendModel? = intent.getSerializableExtra("taste") as TasteSendModel
        Log.e("taste", taste?.toString())
        taste?.let {
            val paramObject = TasteSendModel.toJson(taste)
            Log.e("asd", paramObject.toString())
            NetworkHelper.networkInstance.sendTaste(RequestBody.create(MediaType.parse("application/json"), paramObject.toString()))
                    .enqueue(object : Callback<PlanModel> {
                        override fun onFailure(call: Call<PlanModel>, t: Throwable) {
                            t.printStackTrace()
                            Log.e("asd", t.message)
                        }

                        override fun onResponse(call: Call<PlanModel>, response: Response<PlanModel>) {
                            Log.e("taste input come", response.message() + response.code())
                            if (response.code() == 200) {
                                response.body()?.let {
                                    planModel = it
                                    items.addAll(planModel.dayList[0].schedulelist)
                                    mAdapter.notifyDataSetChanged()
                                }
                            }
                        }

                    })
        }
    }

    private fun iniView() {
        addContainer.setOnClickListener {
            it.visibility = View.GONE
            addFab.visibility = View.VISIBLE
        }

        addFab.setOnClickListener {
            it.visibility = View.GONE
            addContainer.visibility = View.VISIBLE
        }

        backBtn.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.right_in, R.anim.right_out)
        }
        nightPlaceBtn.setOnClickListener(this)
        playingBtn.setOnClickListener(this)
        restaurantBtn.setOnClickListener(this)
        touristBtn.setOnClickListener(this)
        shoppingBtn.setOnClickListener(this)
        parkBtn.setOnClickListener(this)
        calenderList.adapter = mAdapter

        finishBtn.setOnClickListener {
            addSchedule()
        }

        firstDay.setOnClickListener {
            items.clear()
            items.addAll(planModel.dayList[0].schedulelist)
            mAdapter.notifyDataSetChanged()
        }
        secondDay.setOnClickListener {
            items.clear()
            items.addAll(planModel.dayList[1].schedulelist)
            mAdapter.notifyDataSetChanged()
        }
        thirdDay.setOnClickListener {
            items.clear()
            items.addAll(planModel.dayList[2].schedulelist)
            mAdapter.notifyDataSetChanged()
        }
    }


    override fun onClick(v: View) {
        when (v) {
            nightPlaceBtn -> {
                startSearchActivity(customApplication.NIGHT_CATEGORY)
                addContainer.visibility = View.GONE
                addFab.visibility = View.VISIBLE
            }
            playingBtn -> {
                startSearchActivity(customApplication.PLAYING_CATEGORY)
                addContainer.visibility = View.GONE
                addFab.visibility = View.VISIBLE
            }
            restaurantBtn -> {
                startSearchActivity(customApplication.RESTAURANT_CATEGORY)
                addContainer.visibility = View.GONE
                addFab.visibility = View.VISIBLE
            }
            touristBtn -> {
                startSearchActivity(customApplication.TOURIST_CATEGORY)
                addContainer.visibility = View.GONE
                addFab.visibility = View.VISIBLE
            }
            shoppingBtn -> {
                startSearchActivity(customApplication.SHOPPING_CATEGORY)
                addContainer.visibility = View.GONE
                addFab.visibility = View.VISIBLE
            }
            parkBtn -> {
                startSearchActivity(customApplication.PARK_CATEGORY)
                addContainer.visibility = View.GONE
                addFab.visibility = View.VISIBLE
            }
        }
    }

    override fun onBackPressed() {
        if (addContainer.visibility == View.VISIBLE) {

            addContainer.visibility = View.GONE
            addFab.visibility = View.VISIBLE
        } else {
            finish()
            overridePendingTransition(R.anim.right_in, R.anim.right_out)
        }
    }

    private fun startSearchActivity(category: Int) {
        val intent = Intent(this@CalenderActivity, PlaceSearchActivity::class.java)
        intent.putExtra("version", category)
        startActivityForResult(intent, PLACE_SEARCH_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            PLACE_SEARCH_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    val place: Place = data?.getSerializableExtra("place") as Place
                    val scheduleModel = ScheduleModel(place, getStartDate())
                    items.add(scheduleModel)
                    mAdapter.notifyDataSetChanged()
                } else {

                }
            }
        }
    }

    private fun getStartDate(): String {
        val startTime: String = if (items.isEmpty())
            LocalDateTime.parse("2018-10-13T09:00:00").toString()
        else {
            val strDatewithTime = items[items.size - 1].startTime
            val aLDT = LocalDateTime.parse(strDatewithTime).plusHours(1)
            Log.e("asd", "Date with Time: $aLDT")
            aLDT.toString()
        }
        Log.e("asd", startTime.toString())
        return startTime
    }

    private fun addSchedule() {
        NetworkHelper.networkInstance.addSchedule(RequestBody.create(MediaType.parse("application/json"),
                PlanModel.toJson(planModel))).enqueue(object : Callback<PlanModel> {
            override fun onFailure(call: Call<PlanModel>, t: Throwable) {
                t.printStackTrace()
                Log.e("Complete Error", t.message)
            }

            override fun onResponse(call: Call<PlanModel>, response: Response<PlanModel>) {
                if (response.code() == 200) {
                    startActivity(Intent(this@CalenderActivity, AddCompleteActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(applicationContext, "저장에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
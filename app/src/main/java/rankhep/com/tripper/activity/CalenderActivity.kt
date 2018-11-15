package rankhep.com.tripper.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_calendar.*
import okhttp3.MediaType
import okhttp3.RequestBody
import rankhep.com.dhlwn.utils.NetworkHelper
import rankhep.com.tripper.R
import rankhep.com.tripper.adapter.CalenderListAdapter
import rankhep.com.tripper.model.*
import rankhep.com.tripper.util.CustomApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.util.*

class CalenderActivity : AppCompatActivity(), View.OnClickListener, CalenderListAdapter.OnButtonClickedListener {
    private val ADD_PLACE_REQUEST_CODE = 111
    private val EDIT_PLACE_REQUEST_CODE = 222
    private lateinit var customApplication: CustomApplication
    private lateinit var mAdapter: CalenderListAdapter
    private lateinit var planModel: PlanModel
    private var items: ArrayList<ScheduleModel> = ArrayList<ScheduleModel>()
    private var changePosition = -1
    val tabs = ArrayList<TextView>()
    private var selectedDay: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        customApplication = application as CustomApplication
        mAdapter = CalenderListAdapter(items, this)
        iniView()

        getMLData()
    }

    private fun getMLData() {
        val taste: TasteSendModel? = intent.getSerializableExtra("taste") as TasteSendModel
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
                                    setTab()
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

    }


    override fun onClick(v: View) {
        when (v) {
            nightPlaceBtn -> {
                startSearchActivity(customApplication.NIGHT_CATEGORY, selectedDay, planModel.seqnum, false)
                addContainer.visibility = View.GONE
                addFab.visibility = View.VISIBLE
            }
            playingBtn -> {
                startSearchActivity(customApplication.PLAYING_CATEGORY, selectedDay, planModel.seqnum, false)
                addContainer.visibility = View.GONE
                addFab.visibility = View.VISIBLE
            }
            restaurantBtn -> {
                startSearchActivity(customApplication.RESTAURANT_CATEGORY, selectedDay, planModel.seqnum, false)
                addContainer.visibility = View.GONE
                addFab.visibility = View.VISIBLE
            }
            touristBtn -> {
                startSearchActivity(customApplication.TOURIST_CATEGORY, selectedDay, planModel.seqnum, false)
                addContainer.visibility = View.GONE
                addFab.visibility = View.VISIBLE
            }
            shoppingBtn -> {
                startSearchActivity(customApplication.SHOPPING_CATEGORY, selectedDay, planModel.seqnum, false)
                addContainer.visibility = View.GONE
                addFab.visibility = View.VISIBLE
            }
            parkBtn -> {
                startSearchActivity(customApplication.PARK_CATEGORY, selectedDay, planModel.seqnum, false)
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

    private fun startSearchActivity(category: Int, dayNum: Int, seqNum: Int, isEdit: Boolean) {
        val intent = Intent(this@CalenderActivity, PlaceSearchActivity::class.java)
        intent.putExtra("version", category)
        intent.putExtra("dayNum", dayNum)
        intent.putExtra("seqNum", seqNum)
        if (isEdit) {
            startActivityForResult(intent, EDIT_PLACE_REQUEST_CODE)
        } else {
            startActivityForResult(intent, ADD_PLACE_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            ADD_PLACE_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    val place: Place = data?.getSerializableExtra("place") as Place
                    val scheduleModel = ScheduleModel(place, getStartDate())
                    items.add(scheduleModel)
                    mAdapter.notifyDataSetChanged()
                    //TODO : 서버연동
                } else {

                }
            }
            EDIT_PLACE_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    val place: Place = data?.getSerializableExtra("place") as Place
                    val scheduleModel = ScheduleModel(place, items[changePosition].startTime)
                    val sendModel = UpdateModel.getJson(UpdateModel(items[changePosition].place.place_num,
                            selectedDay,
                            place.place_num,
                            planModel.seqnum,
                            planModel.user))
                    NetworkHelper.networkInstance.uploadSchedule(RequestBody.create(MediaType.parse("application/json"), sendModel))
                            .enqueue(object :Callback<PlanModel>{
                                override fun onFailure(call: Call<PlanModel>, t: Throwable) {
                                    t.printStackTrace()
                                }

                                override fun onResponse(call: Call<PlanModel>, response: Response<PlanModel>) {
                                    if(response.isSuccessful){
                                        items[changePosition] = scheduleModel
                                        mAdapter.notifyDataSetChanged()
                                        items[changePosition].place.place_num
                                    }else{
                                        Toast.makeText(this@CalenderActivity, "변경에 실패했습니다.",Toast.LENGTH_SHORT).show()
                                    }
                                }

                            })
                } else {

                }
            }
        }
    }

    private fun getStartDate(): String {
        return if (items.isEmpty())
            LocalDateTime.parse("2018-10-13T09:00:00").toString()
        else {
            val strDatewithTime = items[items.size - 1].startTime
            val aLDT = LocalDateTime.parse(strDatewithTime).plusHours(1)
            Log.e("asd", "Date with Time: $aLDT")
            aLDT.toString()
        }
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

    override fun changeButtonClickedListener(v: View, position: Int, item: ScheduleModel) {
        startSearchActivity(item.place.type, selectedDay, planModel.seqnum, true)
        changePosition = position
    }

    override fun deleteButtonClickedListener(v: View, position: Int, item: ScheduleModel) {
        items.removeAt(position)
        mAdapter.notifyDataSetChanged()
        //TODO : 서버연동
    }


    private fun setTab() {
        val param = LinearLayout.LayoutParams(resources.getDimension(R.dimen.tab_textview_size).toInt() * planModel.dayList.size, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
        dayContainer.layoutParams = param
        planModel.dayList.forEach {
            val textView = TextView(this@CalenderActivity)
            textView.run {
                layoutParams = LinearLayout.LayoutParams(resources.getDimension(R.dimen.tab_textview_size).toInt(), LinearLayout.LayoutParams.MATCH_PARENT)
                text = "${it.day} day"
                Log.e("asd", it.day)
                gravity = Gravity.CENTER
                setTextColor(R.color.colorPrimary)
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
                typeface = Typeface.DEFAULT_BOLD
                setOnClickListener { view: View ->
                    items.clear()
                    items.addAll(planModel.dayList[Integer.parseInt(it.day) - 1].schedulelist)
                    mAdapter.notifyDataSetChanged()
                    setTextFocus(Integer.parseInt(it.day) - 1)
                    setTabSpace(Integer.parseInt(it.day) - 1 + 0.0f)
                    selectedDay = Integer.parseInt(it.day)
                }
            }
            tabs.add(textView)
            dayContainer.addView(textView)
        }

        tabSpaceContainer.run {
            layoutParams = LinearLayout.LayoutParams(resources.getDimension(R.dimen.tab_textview_size).toInt() * planModel.dayList.size, 1)
            weightSum = planModel.dayList.size + 0.0f
        }
    }

    private fun setTextFocus(position: Int) {
        tabs.forEach {
            it.setTypeface(Typeface.DEFAULT)
            it.setTextColor(R.color.textGray)
        }
        tabs[position].typeface = Typeface.DEFAULT_BOLD
        tabs[position].setTextColor(R.color.colorPrimary)
    }

    private fun setTabSpace(weight: Float, speed: Int = 150) {
        val params = space.layoutParams as LinearLayout.LayoutParams
        val startWeight = params.weight
        var operand = params.weight - weight
        (1..speed).forEach { i ->
            Handler().postDelayed({
                params.weight = startWeight - (operand / speed) * i
                space.layoutParams = params
            }, i.toLong())
        }
    }
}
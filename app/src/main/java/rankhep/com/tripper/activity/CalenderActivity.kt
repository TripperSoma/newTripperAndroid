package rankhep.com.tripper.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_calendar.*
import rankhep.com.tripper.R
import rankhep.com.tripper.adapter.CalenderListAdapter
import rankhep.com.tripper.model.Place
import rankhep.com.tripper.model.ScheduleModel
import rankhep.com.tripper.utils.CustomApplication
import java.util.*

class CalenderActivity : AppCompatActivity(), View.OnClickListener {
    val PLACE_SEARCH_REQUEST_CODE = 111
    private lateinit var customApplication: CustomApplication
    private lateinit var mAdapter: CalenderListAdapter
    private var items: ArrayList<ScheduleModel> = ArrayList<ScheduleModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        customApplication = application as CustomApplication
        mAdapter = CalenderListAdapter(items)
        iniView()
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
            startActivity(Intent(this@CalenderActivity, AddCompleteActivity::class.java))
            finish()
        }
    }


    override fun onClick(v: View) {
        when (v) {
            nightPlaceBtn -> {
                startSearchActivity(customApplication.NIGHT_CATEGORY)
            }
            playingBtn -> {
                startSearchActivity(customApplication.PLAYING_CATEGORY)
            }
            restaurantBtn -> {
                startSearchActivity(customApplication.RESTAURANT_CATEGORY)
            }
            touristBtn -> {
                startSearchActivity(customApplication.TOURIST_CATEGORY)
            }
            shoppingBtn -> {
                startSearchActivity(customApplication.SHOPPING_CATEGORY)
            }
            parkBtn -> {
                startSearchActivity(customApplication.PARK_CATEGORY)
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
                    val startTime: Date = if (items.isEmpty())
                        Date(2018, 9, 10, 9, 0, 0)
                    else
                        Date(items[items.size - 1].startTime.year,
                                items[items.size - 1].startTime.month,
                                items[items.size - 1].startTime.day,
                                items[items.size - 1].startTime.hours + 1, 0, 0)
                    val scheduleModel = ScheduleModel(place, startTime)
                    items.add(scheduleModel)
                    mAdapter.notifyDataSetChanged()
                } else {

                }
            }
        }
    }
}
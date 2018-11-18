package rankhep.com.tripper.activity

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
import kotlinx.android.synthetic.main.activity_review_edit.*
import rankhep.com.dhlwn.utils.NetworkHelper
import rankhep.com.tripper.R
import rankhep.com.tripper.adapter.ReviewEditAdapter
import rankhep.com.tripper.model.Review
import rankhep.com.tripper.model.ReviewDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ReviewEditActivity : AppCompatActivity() {
    val tabs = ArrayList<TextView>()
    var reviewNum = 0
    val items = ArrayList<ReviewDetail>()
    var mAdapter = ReviewEditAdapter(items)
    private lateinit var reviewModel: Review
    var selectedDay: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_edit)

        reviewNum = intent.getIntExtra("reviewnum", 1)
        getReviewData(reviewNum)
    }

    fun initView() {
        setTab()
        reviewEditList.adapter = mAdapter
        backBtn.setOnClickListener {
            finish()
        }
    }

    private fun getReviewData(reviewNum: Int) {
        NetworkHelper.networkInstance.getReviewDetail(reviewNum).enqueue(object : Callback<Review> {
            override fun onFailure(call: Call<Review>, t: Throwable) {
                t.printStackTrace()
                Log.e("get review data error", t.message)
            }

            override fun onResponse(call: Call<Review>, response: Response<Review>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        reviewModel = it
                        items.addAll(reviewModel.days[0].detailDTOS)
                        initView()
                    }
                } else {
                    Log.e("get review data error", response.code().toString())
                }
            }

        })
    }


    private fun setTab() {
        val param = LinearLayout.LayoutParams(resources.getDimension(R.dimen.tab_textview_size).toInt() * reviewModel.days.size, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
        dayContainer.layoutParams = param
        reviewModel.days.forEach {
            val textView = TextView(this@ReviewEditActivity)
            textView.run {
                layoutParams = LinearLayout.LayoutParams(resources.getDimension(R.dimen.tab_textview_size).toInt(), LinearLayout.LayoutParams.MATCH_PARENT)
                text = "${it.day} day"
                gravity = Gravity.CENTER
                setTextColor(R.color.colorPrimary)
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
                typeface = Typeface.DEFAULT_BOLD
                setOnClickListener { view: View ->
                    items.clear()
                    items.addAll(reviewModel.days[it.day - 1].detailDTOS)
                    mAdapter.notifyDataSetChanged()
                    setTextFocus(it.day - 1)
                    setTabSpace(it.day - 1 + 0.0f)
                    selectedDay = it.day
                }
            }
            tabs.add(textView)
            dayContainer.addView(textView)
        }

        tabSpaceContainer.run {
            layoutParams = LinearLayout.LayoutParams(resources.getDimension(R.dimen.tab_textview_size).toInt() * reviewModel.days.size, 1)
            weightSum = reviewModel.days.size + 0.0f
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

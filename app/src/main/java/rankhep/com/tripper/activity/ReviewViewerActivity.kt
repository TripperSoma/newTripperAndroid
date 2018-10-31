package rankhep.com.tripper.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_review_viewer.*
import rankhep.com.dhlwn.utils.NetworkHelper
import rankhep.com.tripper.R
import rankhep.com.tripper.adapter.ReviewViewerAdapter
import rankhep.com.tripper.model.Review
import rankhep.com.tripper.model.ReviewDay
import rankhep.com.tripper.model.ReviewDetail
import rankhep.com.tripper.model.ReviewListModel
import rankhep.com.tripper.utils.CustomApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReviewViewerActivity : AppCompatActivity() {
    lateinit var review: Review
    val items = ArrayList<ReviewListModel>()
    lateinit var mAdapter: ReviewViewerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_viewer)
        mAdapter = ReviewViewerAdapter(items)
        getReviewData()
        initView()
    }

    private fun initView() {
        reviewList.adapter = mAdapter
    }

    private fun getReviewData() {
        NetworkHelper.networkInstance.getReviewDetail(intent.getIntExtra("reviewNum", 1))
                .enqueue(object : Callback<Review> {
                    override fun onFailure(call: Call<Review>, t: Throwable) {
                        t.printStackTrace()
                        Log.e("review data error", t.message)
                    }

                    override fun onResponse(call: Call<Review>, response: Response<Review>) {
                        response.body()?.let {
                            review = it
                            it.days.forEach {
                                Log.e("asd", it.day.toString())
                            }
                        }
                        changeReviewToReviewListData()
                    }
                })
    }

    private fun changeReviewToReviewListData() {
        review.days.forEach { day: ReviewDay ->
            items.add(ReviewListModel(day.day, CustomApplication.REVIEW_DAY_VIEW_TYPE))
            Log.e("asd", review.days.size.toString())
            day.detailDTOS.forEach { it: ReviewDetail ->
                Log.e("asd", it.schedule.place.name)
                items.add(ReviewListModel(it, CustomApplication.REVIEW_TOURIST_VIEW_TYPE))
            }
        }
        mAdapter.notifyDataSetChanged()
    }
}

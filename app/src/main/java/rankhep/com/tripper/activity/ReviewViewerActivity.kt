package rankhep.com.tripper.activity

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_review_viewer.*
import rankhep.com.dhlwn.utils.NetworkHelper
import rankhep.com.tripper.R
import rankhep.com.tripper.adapter.ReviewViewerAdapter
import rankhep.com.tripper.model.Review
import rankhep.com.tripper.model.ReviewDay
import rankhep.com.tripper.model.ReviewDetail
import rankhep.com.tripper.model.ReviewListModel
import rankhep.com.tripper.util.CustomApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReviewViewerActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {

    private lateinit var review: Review
    private val items = ArrayList<ReviewListModel>()
    private lateinit var mAdapter: ReviewViewerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_viewer)
        mAdapter = ReviewViewerAdapter(items)
        getReviewData()
        initView()
    }

    private fun initView() {
        reviewList.adapter = mAdapter
        toolbarContainer.addOnOffsetChangedListener(this@ReviewViewerActivity)
        backBtn.setOnClickListener{
            finish()
        }
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

                            //TODO : 리뷰 타이틀 추가
                            review = it
                            it.days.forEach {
                                Log.e("asd", it.day.toString())
                            }
                            Picasso.get().load(review.thumb)
                                    .centerCrop()
                                    .resize(thumbImg.measuredWidth, thumbImg.measuredHeight)
                                    .into(thumbImg)
                            reviewNameText.text = review.user
                            reviewViewerTitleText
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


    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        when {
            verticalOffset > -300 -> {
                reviewViewerTitleText.visibility = View.GONE
                val backArrow: Drawable = resources.getDrawable(R.drawable.ic_arrow_back_black_24dp)
                backArrow.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
                backBtn.setImageDrawable(backArrow)
            }
            else -> {
                reviewViewerTitleText.visibility = View.VISIBLE
                val backArrow: Drawable = resources.getDrawable(R.drawable.ic_arrow_back_black_24dp)
                backArrow.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP)
                backBtn.setImageDrawable(backArrow)
            }

        }
    }
}

package rankhep.com.tripper.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.activity_review_edit.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import rankhep.com.dhlwn.utils.NetworkHelper
import rankhep.com.tripper.R
import rankhep.com.tripper.adapter.ReviewEditAdapter
import rankhep.com.tripper.model.PhotoResponseModel
import rankhep.com.tripper.model.Review
import rankhep.com.tripper.model.ReviewDetail
import rankhep.com.tripper.model.ReviewSaveSendModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.util.*


class ReviewEditActivity : AppCompatActivity(), ReviewEditAdapter.ItemClickedListener {

    val tabs = ArrayList<TextView>()
    var reviewNum = 0
    val items = ArrayList<ReviewDetail>()
    var mAdapter = ReviewEditAdapter(items, this)
    private lateinit var reviewModel: Review
    var selectedDay: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_edit)

        reviewNum = intent.getIntExtra("reviewnum", 1)
        getReviewData(reviewNum)
        initView()
    }

    fun initView() {
        backBtn.setOnClickListener {
            finish()
        }
        finishBtn.setOnClickListener {
            val sendModel = ReviewSaveSendModel(reviewNum, ArrayList<ReviewDetail>(), reviewModel.seqnum)
            reviewModel.days.forEach {
                it.detailDTOS?.forEach { review ->
                    sendModel.reviews.add(review)
                }
            }

            NetworkHelper.networkInstance.uploadReview(RequestBody.create(MediaType.parse("application/json"), ReviewSaveSendModel.toJson(sendModel)))
                    .enqueue(object : Callback<ReviewSaveSendModel> {
                        override fun onFailure(call: Call<ReviewSaveSendModel>, t: Throwable) {
                            t.printStackTrace()
                            Log.e("send review error", t.message)
                        }

                        override fun onResponse(call: Call<ReviewSaveSendModel>, response: Response<ReviewSaveSendModel>) {
                            if (response.isSuccessful) {
                                finish()
                                Toast.makeText(applicationContext, "저장에 성공했습니다.", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(applicationContext, "저장에 실했습니다.", Toast.LENGTH_SHORT).show()
                            }
                        }

                    })
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
                        reviewModel.days[0].detailDTOS?.let { items.addAll(it) }
                        setTab()
                        reviewEditList.adapter = mAdapter
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
                    reviewModel.days[it.day - 1].detailDTOS?.let { items.addAll(it) }
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


    override fun addPictureBtnListener(v: View, position: Int) {
        TedPermission.with(this)
                .setPermissionListener(object : PermissionListener {
                    override fun onPermissionGranted() {
                        val intent = Intent()
                        intent.type = "image/*"
                        intent.action = Intent.ACTION_GET_CONTENT
                        startActivityForResult(intent, position)
                    }

                    override fun onPermissionDenied(deniedPermissions: ArrayList<String>?) {
                        Toast.makeText(applicationContext, "권한 설정이 필요해요.", Toast.LENGTH_SHORT).show()
                    }

                })
                .setRationaleMessage("파일 업로드를 위해 파일 저장소의 권한이 필요합니다.")
                .setDeniedMessage("[설정] > [권한] 에서 권한을 허용할 수 있습니다..")
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)
                .check()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            try {
                val uri: Uri = data?.data!!
                val file = File(getRealPathFromURI_API19(uri))

                val requestFile = RequestBody.create(MediaType.parse("multipart/png"), file)
                val imgBody = MultipartBody.Part.createFormData("file", file.name, requestFile)
                val body = RequestBody.create(okhttp3.MultipartBody.FORM, items[requestCode].detailsnum.toString())
                NetworkHelper.networkInstance.uploadReviewPhoto(body, imgBody).enqueue(object : Callback<PhotoResponseModel> {
                    override fun onFailure(call: Call<PhotoResponseModel>, t: Throwable) {
                        t.printStackTrace()
                        Log.e("upload error", t.message)
                    }

                    override fun onResponse(call: Call<PhotoResponseModel>, response: Response<PhotoResponseModel>) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                items[requestCode].photos.add(it.bucket)
                                mAdapter.notifyPhotoChanged()
                            }
                        } else {
                            Toast.makeText(this@ReviewEditActivity, "이미지 업로드에 실패했습니다.", Toast.LENGTH_SHORT).show()
                            Log.e("upload error", "" + response.code())
                        }
                    }

                })
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    private fun getRealPathFromURI_API19(uri: Uri): String {

        var filePath = ""
        val wholeID = DocumentsContract.getDocumentId(uri)

        val id = wholeID.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]

        val column = arrayOf<String>(MediaStore.Images.Media.DATA)

        val sel = MediaStore.Images.Media._ID + "=?"

        val cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                column, sel, arrayOf<String>(id), null)

        val columnIndex = cursor.getColumnIndex(column[0])

        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex)
        }
        cursor.close()
        return filePath
    }

    override fun deleteBtnClickedListener(v: View, position: Int, review: ReviewDetail) {
        review.run{
            content = ""
            photos.clear()
        }
        mAdapter.notifyPhotoChanged()
        mAdapter.notifyDataSetChanged()
    }
}

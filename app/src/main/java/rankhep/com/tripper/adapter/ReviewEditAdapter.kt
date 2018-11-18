package rankhep.com.tripper.adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import rankhep.com.tripper.R
import rankhep.com.tripper.model.ReviewDetail
import rankhep.com.tripper.util.CustomApplication


class ReviewEditAdapter(val items: ArrayList<ReviewDetail>, val listener: ItemClickedListener) : RecyclerView.Adapter<ReviewEditAdapter.ViewHolder>() {

    fun notifyPhotoChanged(){
        mAdapter.notifyDataSetChanged()
    }

    interface ItemClickedListener {
        fun addPictureBtnListener(v: View, position: Int)
    }

    lateinit var mAdapter: ReviewEditPhotoListAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vh = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_review_edit, parent, false), listener)

        return vh
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = items[position].schedule.place
        mAdapter = ReviewEditPhotoListAdapter(items[position].photos)

        holder.run {
            var hour: String = "22"
            var minute: String = "0"
            try {
                hour = items[position].schedule.startTime.split("T")[1].split(":")[0]
                minute = items[position].schedule.startTime.split("T")[1].split(":")[1]
            } catch (e: Exception) {

            }
            timeText.text = "$hour : $minute"
            title.text = place.name
            subTitle.text = place.city
            contentEditText.setText(items[position].content)
            val layoutManager = LinearLayoutManager(v.context, LinearLayoutManager.HORIZONTAL, false)
            pictureList.layoutManager = layoutManager
            pictureList.adapter = mAdapter
            when (place.type) {
                CustomApplication.NIGHT_CATEGORY -> {
                    themeImg.setImageResource(R.drawable.ic_brightness_3_black_24dp)
                }
                CustomApplication.PARK_CATEGORY -> {
                    themeImg.setImageResource(R.drawable.ic_insert_photo_black_24dp)
                }
                CustomApplication.RESTAURANT_CATEGORY -> {
                    themeImg.setImageResource(R.drawable.ic_restaurant_black_24dp)
                }
                CustomApplication.SHOPPING_CATEGORY -> {
                    themeImg.setImageResource(R.drawable.ic_shopping_cart_black_24dp)
                }
                CustomApplication.TOURIST_CATEGORY -> {
                    themeImg.setImageResource(R.drawable.ic_location_on_black_24dp)
                }
                CustomApplication.PLAYING_CATEGORY -> {
                    themeImg.setImageResource(R.drawable.ic_local_play_black_24dp)
                }
            }


        }
    }

    class ViewHolder(val v: View, val listener: ItemClickedListener) : RecyclerView.ViewHolder(v) {
        val title: TextView = v.findViewById(R.id.dailyTitle)
        val timeText: TextView = v.findViewById(R.id.timeText)
        val subTitle: TextView = v.findViewById(R.id.dailySubTitle)
        val deleteBtn: ImageView = v.findViewById(R.id.deleteBtn)
        val themeImg: ImageView = v.findViewById(R.id.themeImg)
        val addPictureBtn: ImageView = v.findViewById(R.id.addPictureBtn)
        val pictureList: RecyclerView = v.findViewById(R.id.pictureList)
        val contentEditText: EditText = v.findViewById(R.id.reviewContent)

        init {
            addPictureBtn.setOnClickListener {
                listener.addPictureBtnListener(it, adapterPosition)
            }
        }
    }

}
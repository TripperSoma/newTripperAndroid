package rankhep.com.tripper.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import rankhep.com.tripper.R
import rankhep.com.tripper.model.ReviewListModel
import rankhep.com.tripper.utils.CustomApplication
import rankhep.com.tripper.view.CustomGridImageView


class ReviewViewerAdapter(var items: ArrayList<ReviewListModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun getItemViewType(position: Int): Int {
        return items[position].viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType === CustomApplication.REVIEW_DAY_VIEW_TYPE) {
            DayViewHolder(inflater.inflate(R.layout.item_day_review, parent, false))
        } else {
            TouristViewHolder(inflater.inflate(R.layout.item_tourist_review, parent, false))
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            CustomApplication.REVIEW_DAY_VIEW_TYPE -> {

            }
            CustomApplication.REVIEW_TOURIST_VIEW_TYPE -> {

            }
        }
    }


    class DayViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var dayText: TextView = v.findViewById<TextView>(R.id.dayText)
        var dayReview: TextView = v.findViewById<TextView>(R.id.dayReview)

    }

    class TouristViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var timeText: TextView = v.findViewById(R.id.timeText)
        var touristNameText: TextView = v.findViewById(R.id.touristName)
        var gridImage: CustomGridImageView = v.findViewById(R.id.gridImage)
        var touristReviewText: TextView = v.findViewById(R.id.touristReviewText)
    }
}
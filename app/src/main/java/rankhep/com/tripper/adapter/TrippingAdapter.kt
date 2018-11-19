package rankhep.com.tripper.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import rankhep.com.tripper.R
import rankhep.com.tripper.model.TrippingListModel

class TrippingAdapter(val items: ArrayList<TrippingListModel>, val listener: OnClickListener) : RecyclerView.Adapter<TrippingAdapter.ViewHolder>() {

    interface OnClickListener {
        fun onDeleteButtonClickedListener(v: View, position: Int, item: TrippingListModel)
        fun onChangeButtonClickedListener(v: View, position: Int, item: TrippingListModel)
        fun onReviewButtonClickedListener(v: View, position: Int, item: TrippingListModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vh = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_tripping, parent, false))
        vh.run {
            tripDeleteImg.setOnClickListener {
                listener.onDeleteButtonClickedListener(it, adapterPosition, items[adapterPosition])

            }

            tripChangeImg.setOnClickListener {
                listener.onChangeButtonClickedListener(it, adapterPosition, items[adapterPosition])
            }

            tripReviewImg.setOnClickListener {
                listener.onReviewButtonClickedListener(it, adapterPosition, items[adapterPosition])
            }
        }
        return vh
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.run {
            if (items[position].title == null ||items[position].title == "" || items[position].title?.isEmpty()!!)
                tripTitleText.text = "제목을 정해주세요"
            else
                tripTitleText.text = items[position].title
            if (items[position].fromdate != null && items[position].toDate != null)
                tripDateText.text = "" + items[position].fromdate!!.split("T")[0] + items[position].toDate!!.split("T")[0]
            else
                tripDateText.text = "미정"
            tripPlaceText.text = items[position].region
        }
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tripTitleText = v.findViewById<TextView>(R.id.tripTitleText)
        val tripPlaceText = v.findViewById<TextView>(R.id.tripPlaceText)
        val tripDeleteImg = v.findViewById<ImageView>(R.id.tripDeleteImg)
        val tripChangeImg = v.findViewById<ImageView>(R.id.tripChangeImg)
        val tripReviewImg = v.findViewById<ImageView>(R.id.tripReviewImg)
        val tripDateText = v.findViewById<TextView>(R.id.tripPlanDateText)
    }
}
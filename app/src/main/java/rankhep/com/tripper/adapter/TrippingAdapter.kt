package rankhep.com.tripper.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import rankhep.com.tripper.R
import rankhep.com.tripper.model.PlanModel

class TrippingAdapter(val items: ArrayList<PlanModel>, val listener: OnClickListener) : RecyclerView.Adapter<TrippingAdapter.ViewHolder>() {

    interface OnClickListener {
        fun onDeleteButtonClickedListener(v: View, position: Int, item: PlanModel)
        fun onChangeButtonClickedListener(v: View, position: Int, item: PlanModel)
        fun onReviewButtonClickedListener(v: View, position: Int, item: PlanModel)
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
            tripTitleText.text = items[position].dayList[position].schedulelist[0].place.country
        }
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tripTitleText = v.findViewById<TextView>(R.id.tripTitleText)
        val tripPlaceText = v.findViewById<TextView>(R.id.tripPlaceText)
        val tripDeleteImg = v.findViewById<ImageView>(R.id.tripDeleteImg)
        val tripChangeImg = v.findViewById<ImageView>(R.id.tripChangeImg)
        val tripReviewImg = v.findViewById<ImageView>(R.id.tripReviewImg)
    }
}
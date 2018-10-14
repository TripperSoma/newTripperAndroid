package rankhep.com.tripper.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import rankhep.com.tripper.R
import rankhep.com.tripper.model.PlanModel

class TrippingAdapter(val items: ArrayList<PlanModel>) : RecyclerView.Adapter<TrippingAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vh = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_tripping, parent, false))
        vh.run {
            tripDeleteImg.setOnClickListener {
                items.removeAt(adapterPosition)
                notifyDataSetChanged()
            }

            tripChangeImg.setOnClickListener {

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
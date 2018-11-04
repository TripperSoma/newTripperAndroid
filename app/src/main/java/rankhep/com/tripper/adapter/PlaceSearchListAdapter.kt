package rankhep.com.tripper.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso
import rankhep.com.tripper.R
import rankhep.com.tripper.model.PlaceSearchModel

class PlaceSearchListAdapter(private val items: ArrayList<PlaceSearchModel>, private val listener: OnItemClickListener) : RecyclerView.Adapter<PlaceSearchListAdapter.ViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(v: View, position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_place, parent, false))
        v.run {
            itemView.setOnClickListener { listener.onItemClick(itemView, adapterPosition) }
        }
        return v
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.run {
            Picasso.get().load(items[position].picture).into(thumbImg)
            title.text = items[position].name
            subTitle.text = items[position].city
        }
    }

    class ViewHolder(val v: View) : RecyclerView.ViewHolder(v) {
        val thumbImg = v.findViewById<RoundedImageView>(R.id.thumbImg)
        val title = v.findViewById<TextView>(R.id.placeTitle)
        val subTitle = v.findViewById<TextView>(R.id.placeSubTitle)

    }
}
package rankhep.com.tripper.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import rankhep.com.tripper.R
import rankhep.com.tripper.model.HotelModel

class TrippingHotelAdapter(val items: ArrayList<HotelModel>, val listener: OnItemClickedListener) : RecyclerView.Adapter<TrippingHotelAdapter.ViewHolder>() {
    interface OnItemClickedListener {
        fun onItemClicked(v: View, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vh = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_tripping_hotel, parent, false))
        vh.run {
            itemView.setOnClickListener {
                listener.onItemClicked(it, adapterPosition)
            }
        }
        return vh
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.run {
            val transformation = RoundedCornersTransformation(4, 1)
            Picasso.get().load(items[position].picture).transform(transformation).into(backgroundImg)
            hotelNameText.text = items[position].name
            locationText.text = items[position].city
        }
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val backgroundImg: ImageView = v.findViewById(R.id.backgroundImg)
        val hotelNameText: TextView = v.findViewById(R.id.hotelNameText)
        val locationText: TextView = v.findViewById(R.id.locationText)
    }
}
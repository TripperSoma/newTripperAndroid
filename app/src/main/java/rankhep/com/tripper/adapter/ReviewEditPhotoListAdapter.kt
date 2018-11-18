package rankhep.com.tripper.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import rankhep.com.tripper.R
import com.squareup.picasso.Picasso
import android.R.attr.radius
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import rankhep.com.dhlwn.utils.NetworkHelper.Companion.url


class ReviewEditPhotoListAdapter(val items: ArrayList<Any>) : RecyclerView.Adapter<ReviewEditPhotoListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vh = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_review_photo, parent, false))
        return vh
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.run {
            items[position]
            if(items[position] is String) {
                val transformation = RoundedCornersTransformation(2, 1)
                Picasso.get().load(items[position] as String).transform(transformation).into(image)
            }else{

            }
        }
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val image: ImageView = v.findViewById(R.id.image)
    }
}
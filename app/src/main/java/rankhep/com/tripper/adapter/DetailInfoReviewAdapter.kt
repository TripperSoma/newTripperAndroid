package rankhep.com.tripper.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import rankhep.com.tripper.R
import rankhep.com.tripper.model.ReviewDTO

class DetailInfoReviewAdapter(val items: List<ReviewDTO>) : RecyclerView.Adapter<DetailInfoReviewAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.run{
            reviewNameText.text = items[position].name
            Picasso.get().load(items[position].url).into(profileImg)
            content.text = items[position].content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false))

    override fun getItemCount(): Int = items.size

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val reviewNameText = v.findViewById<TextView>(R.id.reviewNameText)
        val profileImg = v.findViewById<CircleImageView>(R.id.reviewProfileImage)
        val content = v.findViewById<TextView>(R.id.reviewContent)
    }
}
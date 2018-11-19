package rankhep.com.tripper.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_review_list.view.*
import rankhep.com.tripper.R
import rankhep.com.tripper.model.MainReviewListModel
import java.lang.Exception


class MainReviewAdapter(val listener: OnItemClickedListener) : RecyclerView.Adapter<MainReviewAdapter.ViewHolder>() {
    interface OnItemClickedListener {
        fun onItemClicked(v: View, position: Int, item: MainReviewListModel)
    }

    var items: ArrayList<MainReviewListModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vh = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_review_list, parent, false))

        vh.run {
            itemView.setOnClickListener {
                listener.onItemClicked(itemView, adapterPosition, items[adapterPosition])
            }
        }
        return vh
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.run {
            try{
                Picasso.get().load(items[position].photo).into(img)
            }catch (e:Exception){

            }
            name.text = items[position].writer
            title.text = items[position].title
            date_text.text = items[position].time
        }
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        init {

        }
    }
}
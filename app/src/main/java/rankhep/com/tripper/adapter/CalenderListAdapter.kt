package rankhep.com.tripper.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import rankhep.com.tripper.R
import rankhep.com.tripper.model.PlanListData

class CalenderListAdapter(private val items: ArrayList<PlanListData>) : RecyclerView.Adapter<CalenderListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vh = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_daily, parent, false))

        return vh
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val title = v.findViewById<TextView>(R.id.dailyTitle)
        val subTitle = v.findViewById<TextView>(R.id.dailySubTitle)
        val timeEditText = v.findViewById<EditText>(R.id.timeInputEditText)
        val deleteBtn = v.findViewById<ImageView>(R.id.deleteBtn)
        val changeBtn = v.findViewById<ImageView>(R.id.changeBtn)
    }
}
package rankhep.com.tripper.adapter

import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import rankhep.com.tripper.R
import rankhep.com.tripper.model.ScheduleModel
import rankhep.com.tripper.util.CustomApplication

class CalenderListAdapter(private val items: ArrayList<ScheduleModel>, val listener: OnButtonClickedListener) : RecyclerView.Adapter<CalenderListAdapter.ViewHolder>() {

    interface OnButtonClickedListener {
        fun changeButtonClickedListener(v: View, position: Int, item: ScheduleModel)
        fun deleteButtonClickedListener(v: View, position: Int, item: ScheduleModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vh = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_daily, parent, false))

        vh.run {
            changeBtn.setOnClickListener {
                listener.changeButtonClickedListener(it, adapterPosition, items[adapterPosition])
            }
            deleteBtn.setOnClickListener {
                listener.deleteButtonClickedListener(it, adapterPosition, items[adapterPosition])
            }
            hourEditText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

            })
        }
        return vh
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val place = items[position].place
        holder.run {
            var hour: String = "22"
            var minute: String = "0"
            try {
                hour = items[position].startTime.split("T")[1].split(":")[0]
                minute = items[position].startTime.split("T")[1].split(":")[1]
            } catch (e: Exception) {

            }
            hourEditText.setText("$hour")
            minuteEditText.setText("${minute}0")
            title.text = place.name
            subTitle.text = place.city
            when (place.type) {
                CustomApplication.NIGHT_CATEGORY -> {
                    themeImg.setImageResource(R.drawable.ic_brightness_3_black_24dp)
                }
                CustomApplication.PARK_CATEGORY -> {
                    themeImg.setImageResource(R.drawable.ic_insert_photo_black_24dp)
                }
                CustomApplication.RESTAURANT_CATEGORY -> {
                    themeImg.setImageResource(R.drawable.ic_restaurant_black_24dp)
                }
                CustomApplication.SHOPPING_CATEGORY -> {
                    themeImg.setImageResource(R.drawable.ic_shopping_cart_black_24dp)
                }
                CustomApplication.TOURIST_CATEGORY -> {
                    themeImg.setImageResource(R.drawable.ic_location_on_black_24dp)
                }

                CustomApplication.PLAYING_CATEGORY -> {
                    themeImg.setImageResource(R.drawable.ic_local_play_black_24dp)
                }
            }
        }
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val title = v.findViewById<TextView>(R.id.dailyTitle)
        val subTitle = v.findViewById<TextView>(R.id.dailySubTitle)
        val hourEditText = v.findViewById<EditText>(R.id.hourInputEditText)
        val minuteEditText = v.findViewById<EditText>(R.id.minuteInputEditText)
        val deleteBtn = v.findViewById<ImageView>(R.id.deleteBtn)
        val changeBtn = v.findViewById<ImageView>(R.id.changeBtn)
        val themeImg = v.findViewById<ImageView>(R.id.themeImg)

    }
}
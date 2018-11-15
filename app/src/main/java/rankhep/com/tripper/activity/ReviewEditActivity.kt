package rankhep.com.tripper.activity

import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_calendar.*
import rankhep.com.tripper.R
import java.util.*

class ReviewEditActivity : AppCompatActivity() {
    var tabs = ArrayList<TextView>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_edit)
    }


    private fun setTab() {
//        val param = LinearLayout.LayoutParams(120 * planModel.dayList.size, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
//        dayContainer.layoutParams = param
//        planModel.dayList.forEach {
//            val textView = TextView(this@ReviewEditActivity)
//            textView.run {
//                Log.e("asd", "as")
//                layoutParams = LinearLayout.LayoutParams(120, LinearLayout.LayoutParams.MATCH_PARENT)
//                text = "${it.day} day"
//                gravity = Gravity.CENTER
//                setTextColor(R.color.colorPrimary)
//                setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
//                typeface = Typeface.DEFAULT_BOLD
//                setOnClickListener { view: View ->
//                    items.clear()
//                    items.addAll(planModel.dayList[Integer.parseInt(it.day)].schedulelist)
//                    mAdapter.notifyDataSetChanged()
//                    setTextFocus(Integer.parseInt(it.day) - 1)
//                    setTabSpace(Integer.parseInt(it.day) - 1 as Float)
//                }
//            }
//            tabs.add(textView)
//            dayContainer.addView(textView)
//        }
//
//        tabSpaceContainer.run {
//            layoutParams = LinearLayout.LayoutParams(120 * planModel.dayList.size, 1)
//            weightSum = planModel.dayList.size as Float
//        }
    }

//    private fun setTextFocus(position: Int) {
//        tabs.forEach {
//            it.typeface = Typeface.DEFAULT
//            it.setTextColor(R.color.textGray)
//        }
//        tabs[position].typeface = Typeface.DEFAULT_BOLD
//        tabs[position].setTextColor(R.color.colorPrimary)
//    }
//
//    private fun setTabSpace(weight: Float, speed: Int = 150) {
//        val params = space.layoutParams as LinearLayout.LayoutParams
//        val startWeight = params.weight
//        var operand = params.weight - weight
//        (1..speed).forEach { i ->
//            Handler().postDelayed({
//                params.weight = startWeight - (operand / speed) * i
//                space.layoutParams = params
//            }, i.toLong())
//        }
//    }
}

package rankhep.com.tripper.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_make_schedule.*
import kotlinx.android.synthetic.main.toolbar_account.*
import rankhep.com.tripper.R

class MakeScheduleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_schedule)

        initView()
    }

    private fun initView() {
        backBtn.setOnClickListener {
            val time = timeInputEditText.toString().toInt()

            val intent = Intent(this@MakeScheduleActivity, MakeScheduleActivity::class.java)
            intent.putExtra("time", time)
            startActivity(intent)
        }
    }
}
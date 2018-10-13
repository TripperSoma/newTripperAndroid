package rankhep.com.tripper.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_make_schedule.*
import rankhep.com.tripper.R

class SetMakeScheduleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_schedule)

        nextFab.setOnClickListener {
            startActivity(Intent(this@SetMakeScheduleActivity, SetTasteActivity::class.java).apply {
                putExtra("date", hourInputEditText.text.toString())
            })
            overridePendingTransition(R.anim.left_in, R.anim.left_out)
            finish()
        }

        backBtn.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.right_in, R.anim.right_out)
        }
    }

    override fun onBackPressed() {
        finish()
        overridePendingTransition(R.anim.right_in, R.anim.right_out)
    }
}
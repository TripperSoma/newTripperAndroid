package rankhep.com.tripper.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_set_taste.*
import rankhep.com.tripper.R

class SetTasteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_taste)
        backBtn.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.right_in, R.anim.right_out)
        }

        nextFab.setOnClickListener {
            startActivity(Intent(this@SetTasteActivity, CalenderActivity::class.java))
            overridePendingTransition(R.anim.left_in, R.anim.left_out)

        }
    }
}